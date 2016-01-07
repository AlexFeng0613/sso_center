package com.hsjc.central.controller;

import com.hsjc.central.service.ApiBaseService;
import com.hsjc.central.service.IndexIcosService;
import com.hsjc.central.service.ThirdClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 页面跳转控制类
 */
@Controller
@RequestMapping("/page/")
public class PageController {

    @Autowired
    private ApiBaseService apiBaseService;

    @Autowired
    private ThirdClientsService thirdClientsService;

    @Autowired
    private IndexIcosService indexIcosService;

    /**
     * @author : zga
     * @date : 2015-12-04
     * 用户主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        List<HashMap> list = indexIcosService.getAllIcos();
        model.addAttribute("icons",list);

        return "/user/index";
    }

    /**
     * @author : zga
     * @date : 2015-12-04
     * 退出登录
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        return "redirect:/user/login.html";
    }

    /**
     * @author : zga
     * @date : 2015-12-04
     * 注册页面
     * @param num
     * @param type
     * @param email
     * @param model
     * @return
     */
    @RequestMapping("register/{num}")
    public String registerPage(@PathVariable Integer num,
                               @RequestParam(value = "type",required = false) String type,
                               @RequestParam(value = "email",required = false) String email,
                               Model model){
        switch (num){
            case 2:
                model.addAttribute("type",type);
                break;

            case 3:
                model.addAttribute("email", email);
                break;

            case 4:
                try {
                    //String original = apiBaseService.getDesUtil().decrypt(email);
                    model.addAttribute("email", email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case 5:
                model.addAttribute("email", email);
                break;

            case 6:
                break;
        }

        return "/user/register"+num;
    }

    /**
     * @author : zga
     * @date : 2015-12-11
     *
     * 非授权的错误页面
     * @return
     */
    @RequestMapping("authorizeFailed")
    public String authorizeFailed(){
        return "/authorizeFailed";
    }

}
