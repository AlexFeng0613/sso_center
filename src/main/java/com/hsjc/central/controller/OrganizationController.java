package com.hsjc.central.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.importMongoData.service.OrganizationService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : zga
 * @date : 2015-12-18
 *
 *
 */
//@Controller
//@RequestMapping("/organization/")
public class OrganizationController {
    //@Autowired
    private OrganizationService organizationService;

    /**
     * @author : zga
     * @date : 2015-12-18
     *
     * 导入数据
     *
     * @return
     */
    @RequestMapping("importData")
    public JSONObject importData(){
        JSONObject resultJson = new JSONObject();
//        organizationService.importOrganizationToMysql();
        return resultJson;
    }

}
