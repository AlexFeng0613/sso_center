package com.hsjc.ssoCenter.app.schoolInvite;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.app.base.BaseController;
import com.hsjc.ssoCenter.core.service.SchoolInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "addNewSchoolInvite",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addNewSchoolInvite(@RequestBody JSONObject paramJson){
        JSONObject resultJson = schoolInviteService.addNewSchoolInvite(paramJson);

        return resultJson;
    }
}
