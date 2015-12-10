package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserMain;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface UserMainMapper {
    int insert(UserMain record);

    int insertSelective(UserMain record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMain record);

    int updateByPrimaryKey(UserMain record);

    UserMain selectByPrimaryKey(Integer id);

    UserMain selectByEmail(UserMain userMain);
}