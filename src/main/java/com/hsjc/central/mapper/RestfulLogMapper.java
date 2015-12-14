package com.hsjc.central.mapper;

import com.hsjc.central.domain.RestfulLog;

import java.util.Map;

/**
 * @author : zga
 * @date : 2015-12-10
 */
public interface RestfulLogMapper {
    int insert(Map<String,Object> paramMap);

    int insertSelective(RestfulLog record);

    int deleteByPrimaryKey(Long restlogid);

    int updateByPrimaryKeySelective(RestfulLog record);

    int updateByPrimaryKey(RestfulLog record);

    RestfulLog selectByPrimaryKey(Long restlogid);
}