package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserTeacher;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface UserTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTeacher record);

    int insertSelective(UserTeacher record);

    UserTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTeacher record);

    int updateByPrimaryKey(UserTeacher record);
}