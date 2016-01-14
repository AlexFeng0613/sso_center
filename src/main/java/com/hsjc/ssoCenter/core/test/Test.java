package com.hsjc.ssoCenter.core.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : zga
 * @date : 2015-12-21
 */
public class Test {
    public static void main(String[] args) {
        /*String str = MD5Util.encode("NN2x9mwFvF"+ MD5Util.encode("hsjcsso")+"201512212013");
        System.out.println(str);


        String validatePwd = MD5Util.encode("NN2x9mwFvF" + MD5Util.encode("hsjcsso") + "201512101052");

        System.out.println(validatePwd);*/


        JSONObject resultJson = new JSONObject();
        resultJson.put("flag", false);

        boolean flag = resultJson.getBoolean("flag");
        System.out.println(flag);

        if(flag){
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }
}
