package com.hsjc.central.mapper;

import com.hsjc.central.domain.Restactiontype;

public interface RestactiontypeMapper {
    int insert(Restactiontype record);

    int insertSelective(Restactiontype record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Restactiontype record);

    int updateByPrimaryKey(Restactiontype record);

    Restactiontype selectByPrimaryKey(Long id);
}