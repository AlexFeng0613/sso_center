package com.hsjc.ssoCenter.app.sms;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.app.base.BaseController;
import com.hsjc.ssoCenter.core.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "sendSmsCode",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sendSmsCode(@RequestBody JSONObject paramJson){
        JSONObject resultJson = smsService.sendSmsCode(paramJson);
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
