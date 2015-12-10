package com.hsjc.central.controller;

import com.hsjc.central.domain.UserMain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 第三方相关控制类
 */
@Controller
@RequestMapping("/3rd/")
public class ThirdController extends BaseController{

    /**
     * @author : zga
     * @date : 2015-12-10
     * 登录
     * @return
     */
    @RequestMapping("login")
    public String login(@RequestParam("client_id")String clientId,
                        @RequestParam("openid")String openid,
                        @RequestParam("password")String password,
                        @RequestParam("time")String time){
        //验证clientId


        //验证openid


        //验证password



        UserMain userMain = getCurrentUser();
        if(userMain == null){

        }

        return "";
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     * 同步所有用户
     * @return
     */
    @RequestMapping("getAllOrganization")
    public String getAllOrganization(){
        return "";
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     * 同步增量用户
     * @return
     */
    @RequestMapping("getDifferentOrganization")
    public String getDifferentOrganization(){
        return "";
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     * 同步所有的用户
     * @return
     */
    @RequestMapping("getAllUser")
    public String getAllUser(){
        return "";
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     * 同步增量用户
     * @return
     */
    @RequestMapping("getDifferentUser")
    public String getDifferentUser(){
        return "";
    }
}
