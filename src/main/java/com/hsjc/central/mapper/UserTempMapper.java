package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserTemp;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 临时用户Mapper类
 */
public interface UserTempMapper {
    int insert(UserTemp record);

    int insertSelective(UserTemp record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTemp record);

    int updateByPrimaryKey(UserTemp record);

    int updateStatusByEmial(UserTemp userTemp);

    int updateOrganizationCodeByEmail(UserTemp userTemp);

    UserTemp selectByEmailOrUserNameOrPhone(UserTemp userTemp);

    UserTemp selectByPrimaryKey(Integer id);
}