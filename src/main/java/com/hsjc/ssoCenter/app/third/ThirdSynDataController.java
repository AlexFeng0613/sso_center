package com.hsjc.ssoCenter.app.third;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.app.base.BaseController;
import com.hsjc.ssoCenter.core.domain.ThirdClients;
import com.hsjc.ssoCenter.core.service.ThirdSynDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 第三方接口控制类
 */
@Controller
@RequestMapping("/3rd/")
public class ThirdSynDataController extends BaseController {

    @Autowired
    ThirdSynDataService thirdSynDataService;

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

        ThirdClients res = thirdSynDataService.selectByClientId(thirdClients);
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

        JSONObject resultJson = thirdSynDataService.getAllOrganization(paramJson);

        return resultJson;
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
    public JSONObject getDifferentOrganization(@RequestParam("client_id")String clientId,
                                               @RequestParam("password")String password,
                                               @RequestParam("time")String time){
        JSONObject paramJson = new JSONObject();
        paramJson.put("clientId", clientId);
        paramJson.put("password", password);
        paramJson.put("time", time);

        JSONObject resultJson = thirdSynDataService.getDifferentOrganization(paramJson);


        return resultJson;
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

        JSONObject resultJson = thirdSynDataService.getAllUser(paramJson);

        return resultJson;
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
    public JSONObject getDifferentUser(@RequestParam("client_id")String clientId,
                                       @RequestParam("password")String password,
                                       @RequestParam("time")String time){
        JSONObject paramJson = new JSONObject();
        paramJson.put("clientId", clientId);
        paramJson.put("password", password);
        paramJson.put("time", time);

        JSONObject resultJson = thirdSynDataService.getDifferentUser(paramJson);

        return resultJson;
    }
}
