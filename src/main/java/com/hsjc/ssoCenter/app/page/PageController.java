package com.hsjc.ssoCenter.app.page;

import com.hsjc.ssoCenter.core.service.ApiBaseService;
import com.hsjc.ssoCenter.core.service.IndexIcosService;
import com.hsjc.ssoCenter.core.service.ThirdClientsService;
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

    @RequestMapping("sso/mailbox")
    public String mailBox(){
        return "/yun/mailbox";
    }

    @RequestMapping("sso/backstage_index")
    public String backstage_index(){
        return "/backstage/backstage_index";
    }

    @RequestMapping("sso/user_list")
    public String user_list(){
        return "/backstage/user_list";
    }

    @RequestMapping("sso/new_user")
    public String new_user(){
        return "/backstage/new_user";
    }

    @RequestMapping("sso/template_to")
    public String template_to(){
        return "/backstage/template_to";
    }

    @RequestMapping("sso/platform_list")
    public String template_list(){
        return "/backstage/platform_list";
    }

    @RequestMapping("sso/platform")
    public String platform(){
        return "/backstage/platform";
    }

    @RequestMapping("sso/service_list")
    public String service_list(){
        return "/backstage/service_list";
    }

    @RequestMapping("sso/new_service")
    public String new_service(){
        return "/backstage/new_service";
    }

    @RequestMapping("sso/passion")
    public String passion(){
        return "/yun/passion";
    }

    @RequestMapping("sso/bindEmailSucc")
    public String bindEmailSucc(String email,Model model){
        model.addAttribute("email",email);
        model.addAttribute("targetWebsite","http://mail."+email.substring(email.lastIndexOf("@")+1));
        return "/page/bindEmailSucc";
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * SSO个人中心>>修改密码成功
     *
     * @return
     */
    @RequestMapping("sso/modifyPasswordSucc")
    public String modifyPasswordSucc(){
        return "/page/modifyPasswordSucc";
    }

    /**
     * @author : zga
     * @date : 2016-01-12
     *
     * SSO个人中心>>绑定邀请码成功
     *
     * @return
     */
    @RequestMapping("sso/bindInviteCodeSucc")
    public String bindInviteCodeSucc(){
        return "/page/bindInviteCodeSucc";
    }

}
