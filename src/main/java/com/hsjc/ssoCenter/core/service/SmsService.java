package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015-12-22
 *
 * SMS Service类
 */
@Service
public class SmsService extends ApiBaseService{

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
        JSONObject resultJson = new JSONObject();

        resultJson.put("success",true);

        return resultJson;
    }

}
