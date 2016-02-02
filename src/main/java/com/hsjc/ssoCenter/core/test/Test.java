package com.hsjc.ssoCenter.core.test;

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

        int pageCount = 440 / 200;
        System.out.println(pageCount);
    }



}
