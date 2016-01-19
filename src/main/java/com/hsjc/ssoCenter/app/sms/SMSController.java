package com.hsjc.ssoCenter.app.sms;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.app.base.BaseController;
import com.hsjc.ssoCenter.core.constant.SMSConstant;
import com.hsjc.ssoCenter.core.service.SmsService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
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
    @RequestMapping("sendSmsCode")
    public JSONObject sendSmsCode(@RequestBody JSONObject paramJson){
        JSONObject resultJson = new JSONObject();

        String phone = paramJson.getString("phone");

        TaobaoClient client = new DefaultTaobaoClient(SMSConstant.URL, SMSConstant.APPKEY, SMSConstant.APPSECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName(SMSConstant.SIGNNAME);
        JSONObject sendParamJson = new JSONObject();
        sendParamJson.put("code","");
        sendParamJson.put("product","华师京城云平台");
        req.setSmsParam(sendParamJson.toJSONString());
        req.setRecNum(phone);
        req.setSmsTemplateCode(SMSConstant.TEMPLATECODE);
        try {
            AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);

            System.out.println("Body: " + response.getBody());

            System.out.println("Result: " + response.getResult());

            System.out.println("ErrorCode: " + response.getErrorCode());

            System.out.println("Msg : " + response.getMsg());

            System.out.println("params : " + response.getParams());
        } catch (ApiException e) {
            e.printStackTrace();
        }
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
