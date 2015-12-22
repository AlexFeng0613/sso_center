package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.annotation.SystemLog;
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
     * 同步所有组织机构
     *
     * @param clientId
     * @param password
     * @param time
     * @return
     */
    @RequestMapping("getAllOrganization")
    @ResponseBody
    @SystemLog(actionId = 2,description = "同步所有组织机构",module = "接口同步")
    public JSONObject getAllOrganization(@RequestParam("client_id")String clientId,
                                         @RequestParam("password")String password,
                                         @RequestParam("time")String time,
                                         @RequestParam("currentPage")String currentPage,
                                         @RequestParam("pageSize")String pageSize
                                         ){

        JSONObject paramJson = new JSONObject();
        paramJson.put("clientId", clientId);
        paramJson.put("password", password);
        paramJson.put("time", time);
        paramJson.put("currentPage", currentPage);
        paramJson.put("pageSize", pageSize);

        JSONObject resJsonObject = thirdClientsService.getAllOrganization(paramJson);

        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步增量组织机构
     *
     * @param clientId
     * @param password
     * @param time
     * @return
     */
    @RequestMapping("getDifferentOrganization")
    @ResponseBody
    @SystemLog(actionId = 3,description = "同步增量组织机构",module = "接口同步")
    public JSONObject getDifferentOrganization(@RequestParam("client_id")String clientId,
                                               @RequestParam("password")String password,
                                               @RequestParam("time")String time){
        JSONObject paramJson = new JSONObject();
        paramJson.put("clientId", clientId);
        paramJson.put("password", password);
        paramJson.put("time", time);

        JSONObject resJsonObject = thirdClientsService.getDifferentOrganization(paramJson);


        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步所有的用户
     *
     * @param clientId
     * @param password
     * @param time
     * @return
     */
    @RequestMapping("getAllUser")
    @ResponseBody
    @SystemLog(actionId = 4,description = "同步所有用户",module = "接口同步")
    public JSONObject getAllUser(@RequestParam("client_id")String clientId,
                                 @RequestParam("password")String password,
                                 @RequestParam("time")String time,
                                 @RequestParam("currentPage")String currentPage,
                                 @RequestParam("pageSize")String pageSize){
        JSONObject paramJson = new JSONObject();
        paramJson.put("clientId", clientId);
        paramJson.put("password", password);
        paramJson.put("time", time);
        paramJson.put("currentPage", currentPage);
        paramJson.put("pageSize", pageSize);

        JSONObject resJsonObject = thirdClientsService.getAllUser(paramJson);

        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-10
     *
     * 同步增量用户
     *
     * @param clientId
     * @param password
     * @param time
     * @return
     */
    @RequestMapping("getDifferentUser")
    @ResponseBody
    @SystemLog(actionId = 5,description = "同步增量用户",module = "接口同步")
    public JSONObject getDifferentUser(@RequestParam("client_id")String clientId,
                                       @RequestParam("password")String password,
                                       @RequestParam("time")String time){
        JSONObject paramJson = new JSONObject();
        paramJson.put("clientId", clientId);
        paramJson.put("password", password);
        paramJson.put("time", time);

        JSONObject resJsonObject = thirdClientsService.getDifferentUser(paramJson);

        return resJsonObject;
    }
}
