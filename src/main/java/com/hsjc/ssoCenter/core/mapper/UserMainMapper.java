package com.hsjc.ssoCenter.core.mapper;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.domain.UserMain;

import java.util.List;

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

    int updatePasswordWithId(UserMain userMain);

    int updateInviteCodeAndOrgCode(UserMain userMain);

    int updatePhoneWithUserName(UserMain userMain);

    int updatePersonalInfoWithUserName(UserMain userMain);

    UserMain selectByPrimaryKey(Integer id);

    UserMain findByEmailOrPhoneOrUserName(UserMain userMain);

    Integer selectCountByClientId(UserMain userMain);

    UserMain selectUserByEmailOrPhone(JSONObject paramJson);

    List<UserMain> findUserByUserName(JSONObject paramJson);

    List<UserMain> findUserByEmail(JSONObject paramJson);
}