package com.hsjc.central.controller;

import com.hsjc.central.domain.UserMain;
import org.apache.shiro.SecurityUtils;

/**
 * @author : zga
 * @date : 2015-12-2
 * 基础Controller类
 */
public class BaseController {
    /**
     * @author : zga
     * @date : 2015-12-10
     * 获取user对象
     */
    public static UserMain getCurrentUser() {
        UserMain userMain = (UserMain) SecurityUtils.getSubject().getPrincipal();
        return userMain;
    }
}
