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

    /**
     * @author : zga
     * @date : 2016-1-10
     *
     * 绑定Email成功
     *
     * @param email
     * @param model
     * @return
     */
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

    @RequestMapping("sso/one_manage")
    public String one_manage(){
        return "/backstage/one_manage";
    }

    @RequestMapping("sso/two_manage")
    public String two_manage(){
        return "/backstage/two_manage";
    }

    @RequestMapping("sso/look_details")
    public String look(){
        return "/backstage/look_details";
    }

    @RequestMapping("sso/tissue_list")
    public String tissue_list(){
        return "/backstage/tissue_list";
    }

    @RequestMapping("sso/new_tissue")
    public String new_tissue(){
        return "/backstage/new_tissue";
    }

    @RequestMapping("sso/invitation_manage")
    public String invitation_manage(){
        return "/backstage/invitation_manage";
    }

    @RequestMapping("sso/new_invitation")
    public String new_invitation(){
        return "/backstage/new_invitation";
    }

    @RequestMapping("sso/admin_list")
    public String admin_list(){
        return "/backstage/admin_list";
    }

    @RequestMapping("sso/new_admin")
    public String new_admin(){
        return "/backstage/new_admin";
    }

    @RequestMapping("sso/site_basic")
    public String site_basic(){
        return "/backstage/site_basic";
    }

    @RequestMapping("sso/email_port")
    public String email_port(){
        return "/backstage/email_port";
    }

    @RequestMapping("sso/mess_port")
    public String mess_port(){
        return "/backstage/mess_port";
    }

    @RequestMapping("sso/site_log")
    public String site_log(){
        return "/backstage/site_log";
    }

    @RequestMapping("sso/dispose")
    public String dispose(){
        return "/backstage/dispose";
    }

    @RequestMapping("sso/create_service")
    public String create_service(){
        return "/backstage/create_service";
    }

    @RequestMapping("sso/passion")
    public String passion(){
        return "/yun/passion";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 组织机构列表
     *
     * @return
     */
    @RequestMapping("sso/tissueList")
    public String tissueList(){
        return "/backstage/tissue_list";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 新增组织机构
     * @return
     */
    @RequestMapping("sso/newTissue")
    public String newTissue(){
        return "/backstage/new_tissue";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 邀请码管理
     *
     * @return
     */
    @RequestMapping("sso/invitationManage")
    public String invitationManage(){
        return "/backstage/invitation_manage";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 新增邀请码
     *
     * @return
     */
    @RequestMapping("sso/newInvitation")
    public String newInvitation(){
        return "/backstage/newInvitation";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 管理员列表
     *
     * @return
     */
    @RequestMapping("sso/adminList")
    public String adminList(){
        return "/backstage/admin_list";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 新增管理员
     *
     * @return
     */
    @RequestMapping("sso/newAdmin")
    public String newAdmin(){
        return "/backstage/new_admin";
    }


    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 站点基本设置
     *
     * @return
     */
    @RequestMapping("sso/siteBasic")
    public String siteBasic(){
        return "/backstage/site_basic";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 邮件接口设置
     *
     * @return
     */
    @RequestMapping("sso/emailPort")
    public String emailPort(){
        return "/backstage/email_port";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 短信接口设置
     *
     * @return
     */
    @RequestMapping("sso/messPort")
    public String messPort(){
        return "/backstage/mess_port";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * 站点日志
     *
     * @return
     */
    @RequestMapping("sso/siteLog")
    public String siteLog(){
        return "/backstage/site_log";
    }
}
