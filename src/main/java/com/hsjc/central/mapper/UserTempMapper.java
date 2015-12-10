package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserTemp;

public interface UserTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTemp record);

    int insertSelective(UserTemp record);

    UserTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTemp record);

    int updateByPrimaryKey(UserTemp record);

    int updateStatusByEmial(UserTemp userTemp);

    UserTemp selectByEmailOrUserNameOrPhone(UserTemp userTemp);
}