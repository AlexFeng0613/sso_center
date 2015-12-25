package com.hsjc.central.test;

import com.hsjc.central.util.MD5Util;

/**
 * @author : zga
 * @date : 2015-12-21
 */
public class Test {
    public static void main(String[] args) {
        String str = MD5Util.encode("NN2x9mwFvF"+ MD5Util.encode("hsjcsso")+"201512212013");
        System.out.println(str);
    }
}
