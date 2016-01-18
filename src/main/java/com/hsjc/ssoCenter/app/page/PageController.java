package com.hsjc.ssoCenter.app.page;

import com.hsjc.ssoCenter.core.service.IndexIcosService;
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

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * SSO后台管理页面
     *
     * @return
     */
    @RequestMapping("sso/backstageIndex")
    public String backstageIndex(){
        return "/backstage/backstageIndex";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * SSO后台>>用户列表
     *
     * @return
     */
    @RequestMapping("sso/userList")
    public String userList(){
        return "/backstage/userList";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * SSO后台>>新增用户
     *
     * @return
     */
    @RequestMapping("sso/newUser")
    public String newUser(){
        return "/backstage/newUser";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * SSO后台>>模板导入用户
     *
     * @return
     */
    @RequestMapping("sso/templateTo")
    public String templateTo(){
        return "/backstage/templateTo";
    }

    /**
     *
     * SSO后台>>模板导入用户说明
     *
     * @return
     */
    @RequestMapping("sso/template")
    public String template(){
        return "/backstage/template";
    }

    /**
     * @author : zga
     * @date : 2016-1-18
     *
     * SSO后台>>第三方列表
     *
     * @return
     */
    @RequestMapping("sso/platformList")
    public String platformList(){
        return "/backstage/platformList";
    }

    /**
     *
     * SSO后台>>
     *
     * @return
     */
    @RequestMapping("sso/platform")
    public String platform(){
        return "/backstage/platform";
    }

    /**
     *
     * SSO后台>>客服列表
     *
     * @return
     */
    @RequestMapping("sso/serviceList")
    public String serviceList(){
        return "/backstage/serviceList";
    }

    /**
     *
     * SSO后台>>新增客服
     *
     * @return
     */
    @RequestMapping("sso/newService")
    public String newService(){
        return "/backstage/newService";
    }

    /**
     *
     * SSO后台>>一次客服管理
     *
     * @return
     */
    @RequestMapping("sso/oneManage")
    public String oneManage(){
        return "/backstage/oneManage";
    }

    /**
     *
     * SSO后台>>二次客服管理
     *
     * @return
     */
    @RequestMapping("sso/twoManage")
    public String twoManage(){
        return "/backstage/twoManage";
    }

    /**
     *
     * SSO后台>>一次客服管理会话详情
     *
     * @return
     */
    @RequestMapping("sso/lookDetails")
    public String lookDetails(){
        return "/backstage/lookDetails";
    }

    /**
     *
     * SSO后台>>二次客服处理记录
     *
     * @return
     */
    @RequestMapping("sso/dispose")
    public String dispose(){
        return "/backstage/dispose";
    }

    /**
     *
     * SSO后台>>新增客服
     *
     * @return
     */
    @RequestMapping("sso/createService")
    public String createService(){
        return "/backstage/createService";
    }

    /**
     *
     * SSO后台>>重置密码第1步
     *
     * @return
     */
    @RequestMapping("sso/passwordOne")
    public String passwordOne(){
        return "/password/passwordOne";
    }

    /**
     *
     * SSO后台>>重置密码第2步
     *
     * @return
     */
    @RequestMapping("sso/passwordTwo")
    public String passwordTwo(){
        return "/password/passwordTwo";
    }

    /**
     *
     * SSO后台>>重置密码第3步
     *
     * @return
     */
    @RequestMapping("sso/passwordThree")
    public String passwordThree(){
        return "/password/passwordThree";
    }

    /**
     *
     * SSO后台>>
     *
     * @return
     */
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
        return "/backstage/tissueList";
    }

    /**
     *
     * SSO后台>>新增组织机构完成页面
     *
     * @return
     */
    @RequestMapping("sso/createTissue")
    public String createTissue(){
        return "/backstage/createTissue";
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
        return "/backstage/newTissue";
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
        return "/backstage/invitationManage";
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
        return "/backstage/adminList";
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
        return "/backstage/newAdmin";
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
        return "/backstage/siteBasic";
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
        return "/backstage/emailPort";
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
        return "/backstage/messPort";
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
        return "/backstage/siteLog";
    }
}
