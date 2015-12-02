package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserMain;
import org.apache.ibatis.annotations.Param;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface UserMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMain record);

    int insertSelective(UserMain record);

    UserMain selectByPrimaryKey(Integer id);


    UserMain selectByEmail(@Param("email") String email);

    int updateByPrimaryKeySelective(UserMain record);

    int updateByPrimaryKey(UserMain record);
}