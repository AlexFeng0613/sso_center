package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.constant.SMSConstant;
import com.hsjc.ssoCenter.core.util.SSOStringUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015-12-22
 *
 * SMS Service类
 */
@Service
public class SmsService extends ApiBaseService{

    public JSONObject sendSmsCode(JSONObject paramJson){
        JSONObject resultJson = getResultJson();
        String phone = paramJson.getString("phone");

        TaobaoClient client = new DefaultTaobaoClient(SMSConstant.URL, SMSConstant.APPKEY, SMSConstant.APPSECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName(SMSConstant.SIGNNAME);
        JSONObject sendParamJson = new JSONObject();
        String smsSendCode = SSOStringUtil.getRandomString(2,4);

        /**
         * 把验证码放入到Redis缓存中
         */
        insertIntoRedis(phone,smsSendCode,String.class);

        sendParamJson.put("code",smsSendCode);
        sendParamJson.put("product","华师京城云平台");
        req.setSmsParam(sendParamJson.toJSONString());
        req.setRecNum(phone);
        req.setSmsTemplateCode(SMSConstant.TEMPLATECODE);
        try {
            AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
            String errorCode = response.getErrorCode();
            if(StringUtils.isEmpty(errorCode)){
                resultJson.put("success",false);
                return resultJson;
            }
            /*System.out.println("Body: " + response.getBody());
            System.out.println("Result: " + response.getResult());
            System.out.println("ErrorCode: " + response.getErrorCode());
            System.out.println("Msg : " + response.getMsg());
            System.out.println("params : " + response.getParams());*/
        } catch (ApiException e) {
            e.printStackTrace();
            resultJson.put("success",false);
            return resultJson;
        }

        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-22
     *
     * 验证短信验证码
     *
     * @param paramJson
     * @return
     */
    public JSONObject validateSmsCode(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        String phone = paramJson.getString("phone");
        String smsInputCode = paramJson.getString("smsInputCode");

        /**
         * 参数检查,是否为空
         */
        if(StringUtils.isEmpty(smsInputCode)){
            resultJson.put("success",false);
            resultJson.put("message",SMSConstant.NULL_SMS_CODE);
            return resultJson;
        }

        /**
         * 过期检验
         */
        Object object = fetchObject(phone,String.class);
        if(object == null){
            resultJson.put("success",false);
            resultJson.put("message",SMSConstant.INVALID_SMS_CODE);
            return resultJson;
        }

        /**
         * 验证码比对,是否正确
         */
        String smsSendCode = object.toString();
        if(!smsSendCode.equals(smsInputCode)){
            resultJson.put("success",false);
            resultJson.put("message",SMSConstant.ERROR_SMS_CODE);
            return resultJson;
        }

        resultJson.put("message",SMSConstant.RIGHT_SMS_CODE);
        return resultJson;
    }

}
