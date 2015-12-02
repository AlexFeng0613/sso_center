package com.hsjc.central.controller;

import com.hsjc.central.domain.UserMain;
import org.apache.shiro.SecurityUtils;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class BaseController {
    //获取user对象
    public static UserMain getCurrentUser() {
        UserMain userMain = (UserMain) SecurityUtils.getSubject().getPrincipal();
        return userMain;
    }
}
