package com.hsjc.central.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.constant.Constant;
import com.hsjc.central.constant.MailTemplate;
import com.hsjc.central.domain.*;
import com.hsjc.central.mapper.EmailResetPwdMapper;
import com.hsjc.central.mapper.SchoolInviteMapper;
import com.hsjc.central.mapper.UserMainMapper;
import com.hsjc.central.mapper.UserTempMapper;
import com.hsjc.central.util.MD5;
import com.hsjc.central.util.MailUtils;
import com.hsjc.central.util.PasswordHelper;
import com.hsjc.central.util.SSOCenterStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
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
public class UserMainService {
    @Autowired
    private UserMainMapper userMainMapper;

    @Autowired
    private ApiBaseService apiBaseService;

    @Autowired
    private UserTempMapper userTempMapper;

    @Autowired
    private PasswordHelper passwordHelper;

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

        userTemp.setType(type);
        userTemp.setUserName(username);
        userTemp.setPassword(password);
        //设置salt和password
        passwordHelper.encryptPassword(userTemp);
        userTemp.setEmail(email);

        int res = userTempMapper.insert(userTemp);
        if(res > 0){
            //调用Email发送接口发送Email
            try {
                ActivateEmailMess activateEmailMess = new ActivateEmailMess();
                activateEmailMess.setEmail(apiBaseService.getDesUtil().encrypt(email));
                activateEmailMess.setTicket(MD5.encode(Calendar.getInstance().getTime().toString()));

                apiBaseService.insertIntoRedis(email,activateEmailMess,ActivateEmailMess.class);

                String activateURL = "http://localhost:8080/user/activateEmail.html?email=" + activateEmailMess.getEmail() + "&ticket=" +
                    activateEmailMess.getTicket();

                String content = SSOCenterStringUtils.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REG_MESSAGE,"%",email,activateURL,activateURL);
                MailUtils.sendMail(MailTemplate.MAIL_SEND_ACTIVATE_SUBJECT, content, email);
            } catch (Exception e) {
                e.printStackTrace();
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
        SchoolInvite schoolInvite = schoolInviteMapper.selectByInviteCode(paramJson);
        int num = 0;
        if(schoolInvite != null){
            UserTemp userTemp = new UserTemp();
            userTemp.setEmail(paramJson.getString("email"));
            num = userTempMapper.updateOrganizationCodeByEmail(userTemp);
            if(num > 0){
                UserMain userMain = new UserMain();
                UserTemp userTemp1 = userTempMapper.selectByEmailOrUserNameOrPhone(userTemp);
                if(userTemp1 != null){
                    userMain.setEmail(userTemp1.getEmail());
                    userMain.setUserName(userTemp1.getUserName());
                    userMain.setPhone(userTemp1.getPhone());
                    userMain.setCreateTime(userTemp1.getCreateTime());
                    userMain.setSalt(userTemp1.getSalt());
                    userMain.setInvitateCode(userTemp1.getInvitateCode());
                    userMain.setIsDelete(userTemp1.getIsDelete());
                    userMain.setOrganizationCode(userTemp1.getOrganizationCode());
                    userMain.setPassword(userTemp1.getPassword());
                    userMain.setType(userTemp1.getType());
                    userMain.setStatus(userTemp1.getStatus());

                    num = userMainMapper.insert(userMain);
                }
            }
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
     * 忘记密码>发送验证码
     *
     * @param paramJson
     */
    public void sendResetPwdCodeWithEmail(JSONObject paramJson){
        String email = paramJson.getString("email");
        String code = SSOCenterStringUtils.getRandomString(4);
        if(!StringUtils.isEmpty(email)){
            String content = SSOCenterStringUtils.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REST_PASSWORD_MESSAGE,"%",code);

            EmailResetPwd emailResetPwd = new EmailResetPwd();
            emailResetPwd.setCreateTime(new Date());
            emailResetPwd.setEmail(email);
            emailResetPwd.setCode(code);
            emailResetPwd.setState("unuse");
            emailResetPwd.setValidSeconds(600);

            emailResetPwdMapper.insert(emailResetPwd);

            MailUtils.sendMail(MailTemplate.MAIL_SEND_RESET_PASSWORD,content,email);
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
        JSONObject resultJson = new JSONObject();
        resultJson.put("success", true);

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
        JSONObject resultJson = new JSONObject();
        resultJson.put("success",true);

        String email = paramJson.getString("email");
        String password = paramJson.getString("password");

        UserMain userMain = new UserMain();
        userMain.setEmail(email);
        userMain.setPassword(password);
        passwordHelper.encryptPassword(userMain);

        int uNum = userMainMapper.updatePasswordByEmail(email);
        if(uNum <= 0) resultJson.put("success",false);

        return resultJson;
    }

}
