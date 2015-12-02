package com.hsjc.central.service;

import com.hsjc.central.domain.User;
import com.hsjc.central.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015/11/24
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findById(String userName){

        System.out.println(userMapper == null);

        User user = userMapper.selectByUserName(userName);
        return user;
    }
}
