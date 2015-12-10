package com.hsjc.central.util;

/**
 * @author : zga
 * @date : 2015-12-03
 */
public class SSOCenterStringUtils {

    /**
     * @author : zga
     * @date : 2015-12-10
     * 替换字符串中指定的值
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
}
