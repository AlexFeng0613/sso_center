package com.hsjc.central.service;

import com.hsjc.central.domain.UserMain;
import com.hsjc.central.mapper.UserMainMapper;
import com.hsjc.central.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015/11/24
 */
@Service
public class UserService {
    @Autowired
    private UserMainMapper userMainMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    public UserMain findById(String userName){
        UserMain userMain = userMainMapper.selectByUserName(userName);
        return userMain;
    }
}
