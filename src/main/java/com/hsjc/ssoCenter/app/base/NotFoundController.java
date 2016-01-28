package com.hsjc.ssoCenter.app.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : zga
 * @date : 2016-01-22
 *
 * 统一过滤页面
 *
 */
@Controller
public class NotFoundController {
    @RequestMapping("/*")
    public String NotFountError(){
        System.out.println("11111");
        return "redirect:/user/login.html";
    }
}
