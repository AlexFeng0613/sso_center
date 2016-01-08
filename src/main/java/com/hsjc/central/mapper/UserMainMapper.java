package com.hsjc.central.mapper;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.central.domain.UserMain;

/**
 * @author : zga
 * @date : 2015-12-2
 *
 * 用户Mapper接口
 */
public interface UserMainMapper {
    int insert(UserMain userMain);

    int insertSelective(UserMain userMain);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMain userMain);

    int updateByPrimaryKey(UserMain userMain);

    int updatePasswordByEmail(String email);

    int updateEmailWithId(UserMain userMain);

    UserMain selectByPrimaryKey(Integer id);

    UserMain findByEmailOrPhoneOrUserName(UserMain userMain);

    Integer selectCountByClientId(UserMain userMain);

    UserMain selectUserByEmailOrPhone(JSONObject paramJson);
}