package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.base.FastJsonRedisSerializer;
import com.hsjc.ssoCenter.core.constant.Constant;
import com.hsjc.ssoCenter.core.constant.MailTemplate;
import com.hsjc.ssoCenter.core.constant.ThirdSynConstant;
import com.hsjc.ssoCenter.core.domain.*;
import com.hsjc.ssoCenter.core.mapper.EmailSendMapper;
import com.hsjc.ssoCenter.core.mapper.SmsSendMapper;
import com.hsjc.ssoCenter.core.mapper.ThirdClientsMapper;
import com.hsjc.ssoCenter.core.mapper.ThirdSynUserDetailLogMapper;
import com.hsjc.ssoCenter.core.util.DesUtil;
import com.hsjc.ssoCenter.core.util.MD5Util;
import com.hsjc.ssoCenter.core.util.MailUtil;
import com.hsjc.ssoCenter.core.util.SSOStringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * 基本Service类
 */
@Service
public class ApiBaseService {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ThirdClientsMapper thirdClientsMapper;

    @Autowired
    EmailSendMapper emailSendMapper;

    @Autowired
    SmsSendMapper smsSendMapper;

    @Autowired
    ThirdSynUserDetailLogMapper thirdSynUserDetailLogMapper;

    /**
     * @author : zga
     * @date : 2015-12-03
     * 获取缓存对象
     * @param id
     * @param clazz
     * @return
     */
    public Object fetchObject(String id,Class clazz) {
        Object obj = null;

        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(clazz));
        if (redisTemplate.hasKey(id)) {
            obj = redisTemplate.opsForValue().get(id);
        }

        return obj;
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     * 插入Redis缓存
     * @param key
     * @param obj
     * @param clazz
     */
    public void insertIntoRedis(String key, Object obj, Class clazz) {
        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(clazz));
        redisTemplate.opsForValue().set(key, obj, 0);
        redisTemplate.expire(key, Constant.REDIS_FETCH_TIME_OUT,TimeUnit.SECONDS);
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     * 获取DesUtils类
     * @return
     * @throws Exception
     */
    public DesUtil getDesUtil(){
        DesUtil desUtil = null;
        try {
            desUtil = new DesUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desUtil;
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * 发送Email
     *
     * @param email
     * @param apiBaseService
     * @return
     * @throws Exception
     */
    public static JSONObject sendEmail(String email, ApiBaseService apiBaseService, String type) throws Exception {
        JSONObject resultJson = getResultJson();

        ActivateEmailMess activateEmailMess = new ActivateEmailMess();
        activateEmailMess.setEmail(apiBaseService.getDesUtil().encrypt(email));
        activateEmailMess.setTicket(MD5Util.encode(Calendar.getInstance().getTime().toString()));

        apiBaseService.insertIntoRedis(email,activateEmailMess,ActivateEmailMess.class);

        String activateURL = null;
        if("0".equals(type)){
            activateURL = Constant.websiteAddress + "/user/activateEmail.html?email=" + activateEmailMess.getEmail() + "&ticket=" +
                    activateEmailMess.getTicket();
        } else {
            activateURL = Constant.websiteAddress + "/user/activateEmail.html?email=" + activateEmailMess.getEmail() + "&ticket=" +
                    activateEmailMess.getTicket()+"&type="+type;
        }

        String content = SSOStringUtil.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REG_MESSAGE,"%",email,activateURL,activateURL);
        MailUtil.sendMail(MailTemplate.MAIL_SEND_ACTIVATE_SUBJECT, content, email);

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-1-20
     *
     * 插入Email发送表
     *
     * @param email
     * @param apiBaseService
     * @param type
     * @return
     * @throws Exception
     */
    public JSONObject insetSendEmail(String email, ApiBaseService apiBaseService, String type) throws Exception {
        JSONObject resultJson = getResultJson();

        ActivateEmailMess activateEmailMess = new ActivateEmailMess();
        activateEmailMess.setEmail(apiBaseService.getDesUtil().encrypt(email));
        activateEmailMess.setTicket(MD5Util.encode(Calendar.getInstance().getTime().toString()));

        apiBaseService.insertIntoRedis(email,activateEmailMess,ActivateEmailMess.class);

        String activateURL = null;
        if("0".equals(type)){
            activateURL = Constant.websiteAddress + "/user/activateEmail.html?email=" + activateEmailMess.getEmail() + "&ticket=" +
                    activateEmailMess.getTicket();
        } else {
            activateURL = Constant.websiteAddress + "/user/activateEmail.html?email=" + activateEmailMess.getEmail() + "&ticket=" +
                    activateEmailMess.getTicket()+"&type="+type;
        }

        String content = SSOStringUtil.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REG_MESSAGE,"%",email,activateURL,activateURL);
        //MailUtil.sendMail(MailTemplate.MAIL_SEND_ACTIVATE_SUBJECT, content, email);

        EmailSend emailSend = new EmailSend();
        emailSend.setContent(content);
        emailSend.setByModule("");
        emailSend.setEmail(apiBaseService.getDesUtil().encrypt(email));
        emailSend.setSubject(MailTemplate.MAIL_SEND_ACTIVATE_SUBJECT);

        emailSendMapper.insert(emailSend);

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * 返回JsonObject
     *
     * @return
     */
    public static JSONObject getResultJson() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("success",true);
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * 获取当前登录者
     *
     * @return
     */
    public UserMain getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        Object object = session.getAttribute("user");
        if(object == null){
            return null;
        }
        return (UserMain)object;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 根据ClientId查询第三方绑定表
     *
     * @param paramJson
     * @return
     */
    public ThirdClients getThirdClientsByClientId(JSONObject paramJson){
        String clientId = paramJson.getString("clientId");

        ThirdClients paramThirdClients = new ThirdClients();
        paramThirdClients.setClientId(clientId);
        ThirdClients thirdClients = thirdClientsMapper.selectByClientId(paramThirdClients);

        return thirdClients;
    }

    /**
     * @author : zga
     * @date : 2016-1-14
     *
     * 校验client_id和password
     *
     * @param paramJson
     * @return
     */
    public JSONObject validateClientIdAndPassword(JSONObject paramJson,ThirdClients thirdClients) {
        JSONObject resultJson = getResultJson();

        if(thirdClients == null) {
            resultJson.put("flag",false);
            resultJson.put("message", ThirdSynConstant.ERROR_CLIENT_ID);
            return resultJson;
        }

        String client_secret = thirdClients.getClientSecret();
        String password = paramJson.getString("password");
        String time = paramJson.getString("time");
        String validatePwd = MD5Util.encode(client_secret + MD5Util.encode(Constant.publicKey) + time);
        if(!validatePwd.equals(password)){
            resultJson.put("flag",false);
            resultJson.put("message", ThirdSynConstant.ERROR_SSO_PASSWORD);
            return resultJson;
        }
        resultJson.put("flag",true);
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2016-1-26
     *
     * 插入同步用户详情日志
     *
     * @param paramJson
     * @param userId
     * @throws Exception
     */
    public void insertSynUserDeailLog(JSONObject paramJson,Integer userId) throws Exception{
        ThirdSynUserDetailLog thirdSynUserDetailLog = new ThirdSynUserDetailLog();
        thirdSynUserDetailLog.setClientId(paramJson.getString("clientId"));
        thirdSynUserDetailLog.setUserId(userId);
        thirdSynUserDetailLogMapper.insert(thirdSynUserDetailLog);
    }
}
