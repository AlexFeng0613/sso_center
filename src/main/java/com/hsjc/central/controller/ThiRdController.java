package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.domain.ThirdClients;
import com.hsjc.central.service.ThirdClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 第三方相关控制类
 */
@Controller
@RequestMapping("/3rd/")
public class ThirdController extends BaseController{

    @Autowired
    private ThirdClientsService thirdClientsService;

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 登录
     * @return
     */
    @RequestMapping("login")
    public String login(@RequestParam("client_id")String clientId){
        //验证clientId
        ThirdClients thirdClients = new ThirdClients();
        thirdClients.setClientId(clientId);

        ThirdClients res = thirdClientsService.selectByClientId(thirdClients);
        if(res == null) return "redirect:/page/authorizeFailed";

        return "redirect:/user/login";
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步所有用户
     * @return
     */
    @RequestMapping("getAllOrganization")
    @ResponseBody
    public JSONObject getAllOrganization(){
        JSONObject resJsonObject = new JSONObject();



        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步增量用户
     * @return
     */
    @RequestMapping("getDifferentOrganization")
    @ResponseBody
    public JSONObject getDifferentOrganization(){
        JSONObject resJsonObject = new JSONObject();



        return resJsonObject;

    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步所有的用户
     * @return
     */
    @RequestMapping("getAllUser")
    @ResponseBody
    public JSONObject getAllUser(){
        JSONObject resJsonObject = new JSONObject();


        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步增量用户
     * @return
     */
    @RequestMapping("getDifferentUser")
    @ResponseBody
    public JSONObject getDifferentUser(){
        JSONObject resJsonObject = new JSONObject();


        return resJsonObject;
    }
}
