package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserStudent;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface UserStudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStudent record);

    int insertSelective(UserStudent record);

    UserStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStudent record);

    int updateByPrimaryKey(UserStudent record);
}