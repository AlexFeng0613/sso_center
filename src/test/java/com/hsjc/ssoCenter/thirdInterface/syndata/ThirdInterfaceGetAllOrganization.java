package com.hsjc.ssoCenter.thirdInterface.syndata;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.util.HttpRequestUtil;

import java.util.HashMap;

/**
 * @author : zga
 * @date : 2015-12-24
 *
 * SSO接口>>获取所有组织机构
 *
 */
public class ThirdInterfaceGetAllOrganization {
    public static void main(String[] args) {
        String url = "http://localhost:8080/3rd/getAllOrganization.json";

        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("client_id","6NqlFnLG");
        paramMap.put("password","ca0263a6a2c5ee801c49e1c4762ec0de");
        paramMap.put("time","201512212013");
        paramMap.put("currentPage",1);
        paramMap.put("pageSize",10);

        String result = HttpRequestUtil.sendGet(url,paramMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
    }
}