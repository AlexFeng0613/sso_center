package com.hsjc.ssoCenter.core.mapper;

import com.hsjc.ssoCenter.core.domain.UserRole;

/**
 * @author : zga
 * @date : 2016-3-17
 *
 * 用户角色Mapper类
 *
 */
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    UserRole selectByPrimaryKey(Long id);
}