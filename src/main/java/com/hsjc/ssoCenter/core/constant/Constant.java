package com.hsjc.ssoCenter.core.constant;

/**
 * @author : zga
 * @date : 2015-11-24
 *
 * 常量类
 */
public class Constant {

    public static final String NULL_PARAM = "100001";

    public static final String INVALID_CODE = "100002";

    public static final String REG_SUCCESS = "100003";

    public static final String REG_FAIL = "100004";

    public static final String SEND_MAIL_FAIL = "100005";

    public static final String SEND_MAIL_SUCCESS = "100006";

    public static final String NOT_LOGIN = "100007";

    /**
     * 修改密码
     */
    public static final String ERROR_PASSWORD = "100008";

    public static final String MODIFY_PASSWORD_FAILED = "100009";

    public static final String MODIFY_PASSWORD_SUCCESS = "100010";

    /**
     * 绑定邀请码
     */
    public static final String ERROR_INVITE_CODE = "100011";

    public static final String BIND_INVITE_CODE_FAIL = "100012";

    public static final String BIND_INVITE_CODE_SUCCESS = "100013";

    /**
     * 绑定手机号
     */
    public static final String EXISTS_BIND_PHONE = "100014";

    public static final String ERROR_SMS_CODE = "100015";

    public static final String BIND_PHONE_FAIL = "100016";

    public static final String BIND_PHONE_SUCCESS = "100017";

    public static final int REDIS_FETCH_TIME_OUT = 18 * 60 * 1000;

    public static String public_key;

    public static String websiteAddress;
}
