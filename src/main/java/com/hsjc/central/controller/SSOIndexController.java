package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.service.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : zga
 * @date : 2016-01-08
 *
 * SSO后台Controller
 *
 */
@Controller
@RequestMapping("/sso/")
public class SSOIndexController extends BaseController{
    @Autowired
    private UserMainService userMainService;


    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>个人设置
     *
     * @return
     */
    @RequestMapping("personalSettings")
    public String personalSettings(){

        return "/yun/personalSettings";
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>绑定邮箱
     *
     * @return
     */
    @RequestMapping("bindEmail")
    public String bindEmail(){
        return "/yun/mailbox";
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>绑定手机
     *
     * @return
     */
    @RequestMapping("bindPhone")
    public String bindPhone(){
        return "/yun/phone";
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO个人中心>>修改密码
     *
     * @return
     */
    @RequestMapping("modifyPassword")
    public String modifyPassword(){
        return "/yun/password";
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>绑定邀请码
     *
     * @return
     */
    @RequestMapping("bindInviteCode")
    public String bindInviteCode(){
        return "/yun/inviteCode";
    }

    /**
     * @author : zga
     * @date : 2016-01-08
     *
     * SSO后台个人中心>>绑定邮箱 发送Email
     *
     * @param paramJson
     * @return
     */
    @RequestMapping(value = "updateEmail",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateEmail(@RequestBody JSONObject paramJson){
        JSONObject resultJson = userMainService.updateEmail(paramJson);
        return resultJson;
    }
}
