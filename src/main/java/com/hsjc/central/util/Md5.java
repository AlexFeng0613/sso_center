package com.hsjc.central.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by xuebiao on 15/3/4.
 */
public class Md5 {
    public final static String encode(String src) {
        return new Md5Hash(src.toCharArray()).toHex();
    }
}
