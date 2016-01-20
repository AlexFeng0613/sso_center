package com.hsjc.ssoCenter.thirdInterface.syndata;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.constant.Constant;
import com.hsjc.ssoCenter.core.util.HttpRequestUtil;

import java.util.HashMap;

/**
 * @author : zga
 * @date : 2015-12-24
 *
 * SSO接口>获取增量组织机构
 *
 */
public class ThirdInterfaceGetDifferentOrganization {
    public static void main(String[] args) {
        String url = Constant.websiteAddress + "/3rd/getDifferentOrganizatio.json";

        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("client_id","6NqlFnLG");
        paramMap.put("password","ca0263a6a2c5ee801c49e1c4762ec0de");
        paramMap.put("time","201512212013");

        String result = HttpRequestUtil.sendGet(url,paramMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
    }
}
