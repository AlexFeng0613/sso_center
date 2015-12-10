package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserTeacher;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface UserTeacherMapper {
    int insert(UserTeacher record);

    int insertSelective(UserTeacher record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTeacher record);

    int updateByPrimaryKey(UserTeacher record);

    UserTeacher selectByPrimaryKey(Integer id);
}