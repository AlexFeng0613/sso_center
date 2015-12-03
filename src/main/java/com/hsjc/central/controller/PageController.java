package com.hsjc.central.controller;

import com.hsjc.central.constant.RedisConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : zga
 * @date : 2015-11-24
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
    public String registerPage(@PathVariable Integer num,
                               @RequestParam(value = "type",required = false) String type,
                               @RequestParam(value = "email",required = false) String email,
                               Model model){
        System.out.println("redis factory is >>"+RedisConstant.DB_DICT);

        switch (num){
            case 2:
                model.addAttribute("type",type);
                break;
            case 3:
                model.addAttribute("email", email);
                break;

            case 4:
                model.addAttribute("email", email);
                break;

            case 5:
                break;

            case 6:
                break;
        }


        return "user/register"+num;
    }

    @RequestMapping("/login")
    public String login(){
        return "/user/login";
    }

}
