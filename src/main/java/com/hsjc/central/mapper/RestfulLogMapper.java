package com.hsjc.central.mapper;

import com.hsjc.central.domain.RestfulLog;

public interface RestfulLogMapper {
    int insert(RestfulLog record);

    int insertSelective(RestfulLog record);

    int deleteByPrimaryKey(Long restlogid);

    int updateByPrimaryKeySelective(RestfulLog record);

    int updateByPrimaryKey(RestfulLog record);

    RestfulLog selectByPrimaryKey(Long restlogid);
}