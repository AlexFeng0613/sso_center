package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.domain.ActivateEmailMess;
import com.hsjc.central.domain.UserMain;
import com.hsjc.central.service.ApiBaseService;
import com.hsjc.central.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author : zga
 * @date : 2015-11-25
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private ApiBaseService apiBaseService;

    @RequestMapping("save")
    public String save(){
        return "user/save";
    }

    /**
     * @author:zga
     * @date:2015-12-02
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password){
        UsernamePasswordToken upToken = new UsernamePasswordToken(username, password, true);
        try{
            SecurityUtils.getSubject().login(upToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "redirect:/page/login.html";
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

    /**
     * @author : zga
     * @date : 2015-12-03
     * 注册用户
     * @param paramJson
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody JSONObject paramJson, HttpSession session){
        JSONObject result = userService.register(paramJson,session);
        return result;
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     * 激活Email
     * @param email
     * @param ticket
     * @return
     */
    @RequestMapping(value = "activateEmail",method = RequestMethod.GET)
    public String activateEmail(@RequestParam("email")String email,
                                @RequestParam("ticket")String ticket){
        try {
            String originEmail = apiBaseService.getDesUtil().decrypt(email);
            Object obj = apiBaseService.fetchObject(originEmail, ActivateEmailMess.class);

            ActivateEmailMess activateEmailMess = null;
            if(obj == null) {
                //ticket失效,返回错误页面,提示重新注册,或者返回首页

                return "";
            }

            activateEmailMess = (ActivateEmailMess) obj;
            if(!(originEmail.equals(activateEmailMess.getEmail()) && ticket.equals(activateEmailMess.getTicket()))){
                //比对不一致,返回错误页面
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/page/register/4.html?email=" + email;
    }

}
