package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.constant.Constant;
import com.hsjc.ssoCenter.core.constant.MailTemplate;
import com.hsjc.ssoCenter.core.domain.*;
import com.hsjc.ssoCenter.core.mapper.*;
import com.hsjc.ssoCenter.core.util.MailUtil;
import com.hsjc.ssoCenter.core.util.PasswordUtil;
import com.hsjc.ssoCenter.core.util.SSOStringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 用户Service类
 */
@SuppressWarnings("ALL")
@Service
public class UserMainService extends ApiBaseService{
    @Autowired
    private UserMainMapper userMainMapper;

    @Autowired
    private ApiBaseService apiBaseService;

    @Autowired
    private UserTempMapper userTempMapper;

    @Autowired
    private UserTeacherMapper userTeacherMapper;

    @Autowired
    private UserStudentMapper userStudentMapper;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private EmailResetPwdMapper emailResetPwdMapper;

    @Autowired
    private SchoolInviteMapper schoolInviteMapper;

    /**
     * @author : zga
     * @date : 2015-12-03
     *
     * 查询用户
     *
     * param email
     * @return
     */
    public UserMain findByEmailOrPhoneOrUserName(String param){
        UserMain paramUserMain = new UserMain();
        //判断Email
        if(param.indexOf("@") > 0){
            paramUserMain.setEmail(param);
        } else {
            //判断手机号
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(param);
            if (m.matches()) {
                paramUserMain.setPhone(param);
            } else {
                paramUserMain.setUserName(param);
            }
        }

        UserMain userMain = userMainMapper.findByEmailOrPhoneOrUserName(paramUserMain);
        return userMain;
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     *
     * 注册新用户
     *
     * @param paramJson
     * @return
     */
    public JSONObject register(JSONObject paramJson, HttpSession session){
        JSONObject resultJson = new JSONObject();
        resultJson.put("success",false);

        if(paramJson == null){
            resultJson.put("message", Constant.NULL_PARAM);
            return resultJson;
        }

        //判断验证码
        String code = paramJson.getString("code");
        Object sessionCode = session.getAttribute("rand");
        if(org.springframework.util.StringUtils.isEmpty(sessionCode)){
            resultJson.put("message", Constant.INVALID_CODE);
            return resultJson;
        } else {
            if(!sessionCode.toString().equalsIgnoreCase(code)){
                resultJson.put("message", Constant.INVALID_CODE);
                return resultJson;
            }
        }

        UserTemp userTemp = new UserTemp();

        String type = paramJson.getString("type");
        String username = paramJson.getString("username");
        String password = paramJson.getString("password");
        String email = paramJson.getString("email");
        String realName = paramJson.getString("realName");
        String gender = paramJson.getString("gender");

        userTemp.setType(type);
        userTemp.setUserName(username);
        userTemp.setPassword(password);
        //设置salt和password
        passwordUtil.encryptPassword(userTemp);
        userTemp.setEmail(email);
        userTemp.setGender(gender);
        userTemp.setRealName(realName);

        int res = userTempMapper.insert(userTemp);
        if(res > 0){
            //调用Email发送接口发送Email
            try {
                resultJson = sendEmail(email,apiBaseService,"0");
            } catch (Exception e) {
                resultJson.put("message", Constant.SEND_MAIL_FAIL);
                return resultJson;
            }

            resultJson.put("message", Constant.REG_SUCCESS);
            resultJson.put("success", true);
            return resultJson;
        }

        resultJson.put("message", Constant.REG_FAIL);

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-22
     *
     * 注册用户>校验邀请码
     *
     * @param paramJson
     * @return
     */
    public JSONObject checkInviteCode(JSONObject paramJson){
        JSONObject resultJson = new JSONObject();

        SchoolInvite schoolInvite = schoolInviteMapper.selectByInviteCode(paramJson);

        if(schoolInvite == null){
            resultJson.put("success",false);
            return resultJson;
        }

        //邀请码校验成功,更新用户信息
        UserTemp userTemp = new UserTemp();
        userTemp.setEmail(paramJson.getString("email"));
        userTemp.setOrganizationCode(schoolInvite.getSchoolId());
        userTempMapper.updateOrganizationCodeByEmail(userTemp);

        resultJson.put("success",true);

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-22
     *
     * 注册用户>更新用户组织机构(根据邀请码)
     *
     * @param paramJson
     * @return
     */
    public int activateInviteCode(JSONObject paramJson){
        int num = 0;
        UserTemp userTemp = new UserTemp();
        try {
            String original = apiBaseService.getDesUtil().decrypt(paramJson.getString("email"));
            userTemp.setEmail(original);

            UserTemp userTemp1 = userTempMapper.selectByEmailOrUserNameOrPhone(userTemp);

            UserMain userMain = new UserMain();
            if(userTemp1 != null){
                userMain.setUserName(userTemp1.getUserName());
                userMain.setPassword(userTemp1.getPassword());
                userMain.setSalt(userTemp1.getSalt());
                userMain.setPhone(userTemp1.getPhone());
                userMain.setType(userTemp1.getType());
                userMain.setStatus(userTemp1.getStatus());
                userMain.setInviteCode(userTemp1.getInviteCode());
                userMain.setEmail(userTemp1.getEmail());
                userMain.setOrganizationCode(userTemp1.getOrganizationCode());

                num = userMainMapper.insert(userMain);

                if("teacher".equals(userMain.getType())){
                    UserTeacher userTeacher = new UserTeacher();
                    userTeacher.setUserId(userMain.getId());
                    userTeacherMapper.insert(userTeacher);
                }

                if("student".equals(userMain.getType())){
                    UserStudent userStudent = new UserStudent();
                    userStudent.setUserId(userMain.getId());
                    userStudentMapper.insert(userStudent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * @author : zga
     * @date : 2015-12-04
     *
     * 注册用户>更新用户状态
     *
     * @param userTemp
     * @return
     */
    public int activateEmail(UserTemp userTemp){
        if(userTemp == null) return 0;

        if(StringUtils.isEmpty(userTemp.getEmail())) return 0;

        if(StringUtils.isEmpty(userTemp.getStatus())) userTemp.setStatus("activated");

        return userTempMapper.updateStatusByEmial(userTemp);
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>校验用户有效性(email、phone)
     *
     * @param paramJson
     * @return
     */
    public UserMain validateUser(JSONObject paramJson){
        UserMain userMain = userMainMapper.selectUserByEmailOrPhone(paramJson);
        return userMain;
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>发送Email验证码
     *
     * @param paramJson
     */
    public void sendResetPwdCodeWithEmail(JSONObject paramJson){
        String email = paramJson.getString("email");
        String code = SSOStringUtil.getRandomString(4);
        if(!StringUtils.isEmpty(email)){
            String content = SSOStringUtil.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REST_PASSWORD_MESSAGE,"%",code);

            EmailResetPwd emailResetPwd = new EmailResetPwd();
            emailResetPwd.setCreateTime(new Date());
            emailResetPwd.setEmail(email);
            emailResetPwd.setCode(code);
            emailResetPwd.setState("unuse");
            emailResetPwd.setValidSeconds(600);

            emailResetPwdMapper.insert(emailResetPwd);

            MailUtil.sendMail(MailTemplate.MAIL_SEND_RESET_PASSWORD,content,email);
        }
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>验证Email验证码
     *
     * @return
     */
    public JSONObject validateResetPasswordEmailCode(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        String code = paramJson.getString("code");
        String email = paramJson.getString("email");
        EmailResetPwd emailResetPwd = emailResetPwdMapper.selectByEmail(email);

        if(!(StringUtils.isNotEmpty(code) && code.equals(emailResetPwd.getCode()))){
            resultJson.put("success", false);
        }

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>重置密码(使用Email)
     *
     * @param paramJson
     * @return
     */
    public JSONObject resetPasswordWithEmail(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        String email = paramJson.getString("email");
        String password = paramJson.getString("password");

        UserMain userMain = new UserMain();
        userMain.setEmail(email);
        userMain.setPassword(password);
        passwordUtil.encryptPassword(userMain);

        int uNum = userMainMapper.updatePasswordByEmail(email);
        if(uNum <= 0) resultJson.put("success",false);

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>绑定邮箱,发送激活Email
     *
     * @param paramJson
     * @return
     */
    public JSONObject updateEmail(JSONObject paramJson){
        JSONObject resultJson = new JSONObject();

        String email = paramJson.getString("email");

        try {
            resultJson = sendEmail(email,apiBaseService,"1");
            resultJson.put("message",Constant.SEND_MAIL_SUCCESS);
        } catch (Exception e) {
            resultJson.put("success",false);
            resultJson.put("message",Constant.SEND_MAIL_FAIL);
        }
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>绑定邮箱,激活用户Email
     *
     * @param paramJson
     * @return
     */
    public int updateUserMainEmail(UserMain userMain){
        return userMainMapper.updateEmailWithId(userMain);
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * SSO后台个人中心>>修改密码
     *
     * @param paramJson
     * @return
     */
    public JSONObject modifyPassword(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        String oldPassword = paramJson.getString("oldPassword");
        String password = paramJson.getString("password");

        UserMain userMain = getCurrentUser();
        if(userMain == null){
            resultJson.put("success",false);
            resultJson.put("message", Constant.NOT_LOGIN);
            return resultJson;
        } else{
            String userNameSalt = userMain.getCredentialsSalt();
            boolean flag = passwordUtil.validPassword(userNameSalt,oldPassword,userMain.getPassword());
            if(!flag){
                resultJson.put("success",false);
                resultJson.put("message", Constant.ERROR_PASSWORD);
                return resultJson;
            }

            //修改密码
            userMain.setPassword(password);
            passwordUtil.encryptPassword(userMain);
            int num = userMainMapper.updatePasswordWithId(userMain);
            if(num > 0){
                resultJson.put("message", Constant.MODIFY_PASSWORD_SUCCESS);
            } else {
                resultJson.put("success",false);
                resultJson.put("message", Constant.MODIFY_PASSWORD_FAILED);
                return resultJson;
            }
        }
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * SSO后台个人中心>>绑定邀请码
     *
     * @param paramJson
     * @return
     */
    public JSONObject bindInviteCode(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        SchoolInvite schoolInvite = schoolInviteMapper.selectByInviteCode(paramJson);
        if(schoolInvite == null){
            resultJson.put("success",false);
            resultJson.put("message", Constant.ERROR_INVITE_CODE);
            return resultJson;
        } else {
            /**
            #tbschoolinvite
            #更新byUserId useTime

            #tbuesrmain
            #更新invitateCode、organizationCode
            */
            UserMain userMain = getCurrentUser();
            if(userMain == null){
                resultJson.put("success",false);
                resultJson.put("message", Constant.NOT_LOGIN);
                return resultJson;
            }
            schoolInvite.setByUserId(Long.parseLong(userMain.getId().toString()));
            schoolInvite.setUseTime(new Date());
            int num = schoolInviteMapper.updateUseTimeAndByUserId(schoolInvite);
            if(num > 0){
                userMain.setOrganizationCode(schoolInvite.getSchoolId());
                userMain.setInviteCode(schoolInvite.getInviteCode());
                int num1 = userMainMapper.updateInviteCodeAndOrgCode(userMain);
                if(num1 > 0){
                    resultJson.put("message", Constant.BIND_INVITE_CODE_SUCCESS);
                }else{
                    resultJson.put("success",false);
                    resultJson.put("message", Constant.BIND_INVITE_CODE_FAIL);
                    return resultJson;
                }
            } else {
                resultJson.put("success",false);
                resultJson.put("message", Constant.BIND_INVITE_CODE_FAIL);
                return resultJson;
            }
        }
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * SSO后台个人中心>>绑定手机
     *
     * @param paramJson
     * @return
     */
    public JSONObject bindPhone(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        UserMain userMain = userMainMapper.selectUserByEmailOrPhone(paramJson);
        if(userMain != null){
            resultJson.put("success",false);
            resultJson.put("message", Constant.EXISTS_BIND_PHONE);
            return resultJson;
        } else {
            /**
             * 校验验证码(有效性、正确性)
             */

            UserMain currentUser = getCurrentUser();
            currentUser.setPhone(paramJson.getString("phone"));
            int num = userMainMapper.updatePhoneWithUserName(currentUser);
            if(num > 0){
                resultJson.put("message", Constant.BIND_PHONE_SUCCESS);
            }else{
                resultJson.put("success",false);
                resultJson.put("message", Constant.BIND_PHONE_FAIL);
                return resultJson;
            }
        }

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * SSO后台个人中心>>修改个人资料
     *
     * @param userMain
     * @return
     */
    public boolean modifyPersonalInfo(UserMain userMain){
        int num = userMainMapper.updatePersonalInfoWithUserName(userMain);
        if(num > 0){
            return true;
        }
        return false;
    }
}