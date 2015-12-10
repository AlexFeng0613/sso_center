package com.hsjc.central.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.constant.Constant;
import com.hsjc.central.constant.MailTemplate;
import com.hsjc.central.domain.ActivateEmailMess;
import com.hsjc.central.domain.UserMain;
import com.hsjc.central.domain.UserTemp;
import com.hsjc.central.mapper.UserMainMapper;
import com.hsjc.central.mapper.UserTempMapper;
import com.hsjc.central.util.MailUtils;
import com.hsjc.central.util.Md5;
import com.hsjc.central.util.PasswordHelper;
import com.hsjc.central.util.SSOCenterStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * @author : zga
 * @date : 2015-11-24
 */
@Service
public class UserService {
    @Autowired
    private UserMainMapper userMainMapper;

    @Autowired
    private ApiBaseService apiBaseService;

    @Autowired
    private UserTempMapper userTempMapper;

    @Autowired
    private PasswordHelper passwordHelper;


    /**
     * @author : zga
     * @date : 2015-12-03
     * 查询用户
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
        userTemp.setUsername(username);
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
                activateEmailMess.setTicket(Md5.encode(Calendar.getInstance().getTime().toString()));

                apiBaseService.insertIntoRedis(email,activateEmailMess,ActivateEmailMess.class);

                String activateURL = "http://localhost:8080/user/activateEmail.html?email=" + activateEmailMess.getEmail() + "&ticket=" +
                    activateEmailMess.getTicket();

                String content = SSOCenterStringUtils.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REG_MESSAGE,"%",email,activateURL);
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
     * 更新用户状态
     * @param userTemp
     * @return
     */
    public int activateEmail(UserTemp userTemp){
        if(userTemp == null) return 0;

        if(StringUtils.isEmpty(userTemp.getEmail())) return 0;

        if(StringUtils.isEmpty(userTemp.getStatus())) userTemp.setStatus("activated");

        return userTempMapper.updateStatusByEmial(userTemp);
    }


}
