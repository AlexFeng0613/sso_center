package com.hsjc.central.util;

import java.util.Random;

/**
 * @author : zga
 * @date : 2015-11-24
 */
public class CentralStringUtils {
    /**
     * 生成随机字符串
     * @param length  字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String clientId = getRandomString(8);

        String clientSecret = getRandomString(10);

        System.out.println(clientId + "\r\n" + clientSecret);
    }
}
