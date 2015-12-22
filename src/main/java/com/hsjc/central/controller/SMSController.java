package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zga
 * @date : 2015-12-22
 *
 * 短信控制类
 */
@Controller
@RequestMapping("/sms/")
public class SMSController extends BaseController {
    @Autowired
    private SmsService smsService;


    /**
     * @author : zga
     * @date : 2015-12-17
     *
     * SMS发送验证码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("sendSmsCode")
    public JSONObject sendSmsCode(@RequestBody JSONObject paramJson){
        JSONObject resultJson = new JSONObject();
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-17
     *
     * SMS验证短信码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping(value = "validateSmsCode",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject validateSmsCode(@RequestBody JSONObject paramJson){
        JSONObject resultJson = smsService.validateSmsCode(paramJson);
        return resultJson;
    }

}
