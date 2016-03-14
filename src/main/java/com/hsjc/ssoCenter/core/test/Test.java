package com.hsjc.ssoCenter.core.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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


        /*JSONObject resultJson = new JSONObject();
        resultJson.put("flag", false);

        boolean flag = resultJson.getBoolean("flag");
        System.out.println(flag);

        if(flag){
            System.out.println(1);
        } else {
            System.out.println(2);
        }*/

       /* String activateURL = Constant.websiteAddress + "/user/activateEmail.html?email=617542946@qq.com&ticket=" +
                MD5Util.encode(Calendar.getInstance().getTime().toString());


        String content = SSOStringUtil.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REG_MESSAGE,"%","617542946@qq.com",activateURL,activateURL);

        System.out.println(content);*/

        /**
         * 跳出当前for循环
         */
        /*for(int i = 0; i < 5;i++){
            for(int j = 0;j < 1;j++){
                if(i == 3){
                    break;
                }
                System.out.print(i+"\t");
            }
        }

        System.out.println("-----------------");*/
        /**
         * 这个a相当于一个标签,可以直接跳出外层循环.
         */
        /*a : for(int i = 0; i < 5;i++){
            for(int j = 0;j < 1;j++){
                if(i == 3){
                    break a;
                }
                System.out.print(i+"\t");
            }
        }*/

        /*String password = MD5Util.encode("pReVWOmCDQ"+ MD5Util.encode("hsjcsso") + "201601291800");
        System.out.println(password);*/

        /*int pageCount = 440 / 200;
        System.out.println(pageCount);*/


        /**
         * 构造参数 SSO访问Jclass
         */
        /*String time = DateUtil.getCurrentDate("yyyyMMddHHmm");
        System.out.println(time);
        String password = MD5Util.encode("Pxk8Wq9XWe"+MD5Util.encode("hsjcsso")+"201602241535");
        System.out.println(password);*/

        //SSO访问Jclass URl：http://192.168.18.159:8091/load.html?openid=1997&password=cd0319d830a085bc3585eb7733eb01cd&time=201602241535

        /**
         * Jclass访问SSO
         */
        /*String password = MD5Util.encode("NN2x9mwFvF"+ MD5Util.encode("hsjcsso")+"201602241535");
        System.out.println(password);*/


        //int num = Integer.parseInt("123");
        //System.out.println(num);

        Set addSet = new HashSet<>();
        HashMap hashMap = new HashMap();
        hashMap.put("name","zhuzi");
        hashMap.put("code","abc123");

        HashMap hashMap1 = new HashMap();
        hashMap1.put("name","zhuzi");
        hashMap1.put("code","abc123");

        addSet.add(hashMap);
        addSet.add(hashMap1);

        System.out.println(addSet.size());



    }
}
