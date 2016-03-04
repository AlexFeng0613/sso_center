package com.hsjc.ssoCenter.app.schoolInvite;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.app.base.BaseController;
import com.hsjc.ssoCenter.core.service.SchoolInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : zga
 * @date : 2016-02-29
 *
 * 邀请码管理类
 *
 */
@Controller
@RequestMapping("/schoolInvite/")
public class SchoolInviteController extends BaseController {

    @Autowired
    SchoolInviteService schoolInviteService;

    /**
     * @author : zga
     * @date : 2016-2-29
     *
     * 新增邀请码
     *
     * @param paramJson
     * @return
     */
    public JSONObject addNewSchoolInvite(@RequestParam JSONObject paramJson){
        JSONObject resultJson = null;
        return resultJson;
    }
}
