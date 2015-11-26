package com.hsjc.central.controller;

import com.hsjc.central.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : zga
 * @date : 2015/11/25
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @RequestMapping("save")
    public String save(){
        return "user/save";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        System.out.println("login ...");
        return "/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upToken = new UsernamePasswordToken(username, password, true);
        try{
            subject.login(upToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "/login";
        }

        User user = (User) subject.getPrincipal();

        Session session = subject.getSession(true);
        session.setTimeout(-1);
        session.setAttribute("user", user);

        return "redirect:index.html";
    }

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("username","yeyinzhu");
        return "index";
    }
}
