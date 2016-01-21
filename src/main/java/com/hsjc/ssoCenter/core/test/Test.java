package com.hsjc.ssoCenter.core.test;

import com.hsjc.ssoCenter.core.constant.Constant;
import com.hsjc.ssoCenter.core.constant.MailTemplate;
import com.hsjc.ssoCenter.core.util.MD5Util;
import com.hsjc.ssoCenter.core.util.SSOStringUtil;

import java.util.Calendar;

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

        String activateURL = Constant.websiteAddress + "/user/activateEmail.html?email=617542946@qq.com&ticket=" +
                MD5Util.encode(Calendar.getInstance().getTime().toString());


        String content = SSOStringUtil.replaceAllWithSplitStr(MailTemplate.MAIL_SEND_REG_MESSAGE,"%","617542946@qq.com",activateURL,activateURL);

        System.out.println(content);
    }
}
