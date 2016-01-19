package com.hsjc.ssoCenter.core.util;

import java.util.Random;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * 字符串工具类
 */
public class SSOStringUtil {

    public static String baseRandomString = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String baseRandomSmsCode = "1234567890";

    /**
     * @author : zga
     * @date : 2015-12-10
     * 替换字符串中指定的值
     *
     * explain:
     * src : Hello,%,Welcome to %.
     * split : %
     * args : Allen,HSJC.
     * result: Hello,Allen,Welcome to HSJC.
     * @param src
     * @param split
     * @param args
     * @return
     */
    public static String replaceAllWithSplitStr(String src, String split, Object... args) {
        int i = 0;
        int index = src.indexOf(split);
        while (index > -1 && i < args.length) {
           src = src.replaceFirst(split, args[i].toString());
            i++;
        }
        return src;
    }

    /**
     * 生成随机字符串
     * @param length  字符串长度
     * @return
     */
    public static String getRandomString(int type,int length) {
        String base;
        if(type == 1){
            base = baseRandomString;
        } else {
            base = baseRandomSmsCode;
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String clientId = getRandomString(1,8);

        String clientSecret = getRandomString(1,10);

        String ssoPassword = getRandomString(1,10);

        System.out.println(clientId + "\r\n" + clientSecret + "\r\n" + ssoPassword);
    }
}
