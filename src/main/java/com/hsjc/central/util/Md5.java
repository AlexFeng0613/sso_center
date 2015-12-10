package com.hsjc.central.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author : zga
 * @date : 2015-12-04
 *
 * MD5工具类
 */
public class MD5 {
    public final static String encode(String src) {
        return new Md5Hash(src.toCharArray()).toHex();
    }
}
