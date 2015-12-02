package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.domain.UserMain;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : zga
 * @date : 2015/11/25
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{

    @RequestMapping("save")
    public String save(){
        return "user/save";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password){
        UsernamePasswordToken upToken = new UsernamePasswordToken(username, password, true);
        try{
            SecurityUtils.getSubject().login(upToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "/login";
        }

        Subject subject = SecurityUtils.getSubject();

        UserMain userMain = (UserMain) subject.getPrincipal();

        Session session = subject.getSession(true);
        session.setTimeout(-1);
        session.setAttribute("user", userMain);

        return "redirect:index.html";
    }

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("username","yeyinzhu");
        return "user/index";
    }

    @RequestMapping("test")
    @ResponseBody
    public JSONObject test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag","true");
        jsonObject.put("message","Test Success!");
        return jsonObject;
    }
}
