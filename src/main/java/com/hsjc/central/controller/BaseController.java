package com.hsjc.central.controller;

import com.hsjc.central.domain.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author : zga
 * @date : 2015/12/2
 */
public class BaseController {
    //获取user对象
    public static User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
