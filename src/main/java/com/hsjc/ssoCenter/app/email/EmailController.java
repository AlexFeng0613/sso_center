package com.hsjc.ssoCenter.app.email;

import com.hsjc.ssoCenter.app.base.BaseController;
import com.hsjc.ssoCenter.core.domain.SystemProperties;
import com.hsjc.ssoCenter.core.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/27.
 * 邮件控制类
 */
@Controller
@RequestMapping("/email/")
public class EmailController extends BaseController {
    @Autowired
    private EmailService emailService;

    /**
     * 短信配置显示
     * @param model
     * @return
     */
    @RequestMapping("emailPort")
    public String messPort(Model model){
        List<SystemProperties> list = new ArrayList<>();
        list = emailService.findEmail();
        model.addAttribute("emailList",list);
        return "emailPort";
    }
}
