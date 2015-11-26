package com.hsjc.central.controller;

import org.springframework.stereotype.Controller;
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
        System.out.println("index ....");
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        System.out.println("logout ...");
        return "logout";
    }
}
