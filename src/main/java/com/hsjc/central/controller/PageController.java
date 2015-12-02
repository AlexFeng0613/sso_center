package com.hsjc.central.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : zga
 * @date : 2015/11/24
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }

    @RequestMapping("/register/{num}")
    public String registerPage(@PathVariable Integer num){
        return "user/register"+num;
    }

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }

}
