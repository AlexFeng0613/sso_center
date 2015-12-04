package com.hsjc.central.mapper;

import com.hsjc.central.domain.UserTemp;
import org.apache.ibatis.annotations.Param;

public interface UserTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("userTemp") UserTemp record);

    int insertSelective(UserTemp record);

    UserTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTemp record);

    int updateByPrimaryKey(UserTemp record);
}