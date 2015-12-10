package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.domain.ActivateEmailMess;
import com.hsjc.central.domain.UserMain;
import com.hsjc.central.domain.UserTemp;
import com.hsjc.central.service.ApiBaseService;
import com.hsjc.central.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * @author : zga
     * @date : 2015-12-04
     * 登录页面
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    /**
     * @author:zga
     * @date:2015-12-02
     * 用户登录
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
            return "redirect:login.html";
        }

        Subject subject = SecurityUtils.getSubject();

        UserMain userMain = (UserMain) subject.getPrincipal();

        Session session = subject.getSession(true);
        session.setTimeout(-1);
        session.setAttribute("user", userMain);

        return "redirect:/page/index.html";
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
            //验证email、ticket是否正确,ticket是否过期
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

            //如果正确且没有过期,更新状态为activated
            UserTemp userTemp = new UserTemp();
            userTemp.setEmail(originEmail);
            userTemp.setStatus("activated");
            int num = userService.activateEmail(userTemp);
            if(num < 1) return "";//激活失败,返回错误页面
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/page/register/4.html?email=" + email;
    }

}
