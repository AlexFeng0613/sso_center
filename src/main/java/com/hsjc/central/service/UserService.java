package com.hsjc.central.service;

import com.hsjc.central.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015/11/24
 */
@Service
public class UserService {

    public User findById(String userId){
        User user = new User();
        user.setUsername("yeyinzhu");
        user.setPassword("000000");
        return user;
    }
}
