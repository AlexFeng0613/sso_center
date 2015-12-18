package com.hsjc.central.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.constant.Constant;
import com.hsjc.central.constant.MailTemplate;
import com.hsjc.central.domain.ActivateEmailMess;
import com.hsjc.central.domain.EmailResetPwd;
import com.hsjc.central.domain.UserMain;
import com.hsjc.central.domain.UserTemp;
import com.hsjc.central.mapper.EmailResetPwdMapper;
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

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 用户Service类
 */
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


    /**
     * @author : zga
     * @date : 2015-12-03
     *
     * 查询用户
     *
     * param email
     * @return
     */
    public UserMain findByEmail(String email){
        UserMain paramUserMain = new UserMain();
        paramUserMain.setEmail(email);
        UserMain userMain = userMainMapper.selectByEmail(paramUserMain);
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
        JSONObject result = new JSONObject();
        result.put("success",false);

        if(paramJson == null){
            result.put("message", Constant.NULL_PARAM);
            return result;
        }

        //判断验证码
        String code = paramJson.getString("code");
        Object sessionCode = session.getAttribute("rand");
        if(org.springframework.util.StringUtils.isEmpty(sessionCode)){
            result.put("message", Constant.INVALID_CODE);
            return result;
        } else {
            if(!sessionCode.toString().equalsIgnoreCase(code)){
                result.put("message", Constant.INVALID_CODE);
                return result;
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
                result.put("message", Constant.SEND_MAIL_FAIL);
                return result;
            }

            result.put("message", Constant.REG_SUCCESS);
            result.put("success", true);
            return result;
        }

        result.put("message", Constant.REG_FAIL);

        return result;
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
