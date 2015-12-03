package com.hsjc.central.util;

/**
 * @author : zga
 * @date : 2015-12-03
 */
public class SSOCenterStringUtils {

    public static String replaceAllWithSplitStr(String src, String split, Object... args) {
        int i = 0;
        int index = src.indexOf(split);
        while (index > -1 && i < args.length) {
           src = src.replaceFirst(split, args[i].toString());
            i++;
        }
        return src;
    }
}
