package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.annotation.SystemLog;
import com.hsjc.central.domain.ActivateEmailMess;
import com.hsjc.central.domain.UserMain;
import com.hsjc.central.domain.UserTemp;
import com.hsjc.central.service.ApiBaseService;
import com.hsjc.central.service.UserMainService;
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
 *
 * 用户控制类
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{

    @Autowired
    private UserMainService userMainService;

    @Autowired
    private ApiBaseService apiBaseService;

    /**
     * @author : zga
     * @date : 2015-12-04
     *
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    /**
     * @author:zga
     * @date:2015-12-02
     *
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @SystemLog(actionId = 1,description = "用户登录",module = "登录")
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
     *
     * 注册用户
     *
     * @param paramJson
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody JSONObject paramJson, HttpSession session){
        JSONObject result = userMainService.register(paramJson,session);
        return result;
    }

    /**
     * @author : zga
     * @date : 2015-12-03
     *
     * 注册用户>激活Email
     *
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
            int num = userMainService.activateEmail(userTemp);
            if(num < 1) return "";//激活失败,返回错误页面
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/page/register/4.html?email=" + email;
    }

    /**
     * @author : zga
     * @date : 2015-12-22
     *
     * 注册用户>校验邀请码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping(value = "checkInviteCode",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkInviteCode(@RequestBody JSONObject paramJson){
        JSONObject resultJson = userMainService.checkInviteCode(paramJson);
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-22
     *
     * 注册用户>更新用户组织机构(根据邀请码)
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("activateInviteCode")
    public String activateInviteCode(@RequestBody JSONObject paramJson){
        int num = userMainService.activateInviteCode(paramJson);
        if(num <= 0){
            //
            return "";
        }
        return "redirect:/page/register/6.html";
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>校验用户是否存在
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("validateUser")
    @ResponseBody
    public JSONObject validateUser(@RequestBody JSONObject paramJson){
        JSONObject resultJson = new JSONObject();
        UserMain userMain = userMainService.validateUser(paramJson);
        if(userMain == null)
            resultJson.put("success",false);

        resultJson.put("success",true);
        return resultJson;
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>发送验证码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("sendResetPwdCodeWithEmail")
    @ResponseBody
    public JSONObject sendResetPwdCodeWithEmail(@RequestBody JSONObject paramJson){
        userMainService.sendResetPwdCodeWithEmail(paramJson);
        return null;
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>验证Email验证码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("validateResetPasswordEmailCode")
    @ResponseBody
    public JSONObject validateResetPasswordEmailCode(@RequestBody JSONObject paramJson){
        return userMainService.validateResetPasswordEmailCode(paramJson);
    }

    /**
     * @author : zga
     * @date : 2015-12-16
     *
     * 忘记密码>重置密码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("resetPasswordWithEmail")
    @ResponseBody
    public JSONObject resetPasswordWithEmail(@RequestBody JSONObject paramJson){
        return userMainService.resetPasswordWithEmail(paramJson);
    }

    /**
     * @author : zga
     * @date : 2015-12-17
     *
     * 忘记密码>SMS重置密码
     *
     * @param paramJson
     * @return
     */
    @RequestMapping("resetPasswordWithSms")
    public JSONObject resetPasswordWithSms(@RequestBody JSONObject paramJson){
        JSONObject resultJson = new JSONObject();


        return resultJson;
    }
}
