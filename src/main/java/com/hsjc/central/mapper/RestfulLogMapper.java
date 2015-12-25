package com.hsjc.central.mapper;

import com.hsjc.central.domain.RestfulLog;

import java.util.Map;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 接口日志Mapper类
 *
 */
public interface RestfulLogMapper {
    int insert(Map<String,Object> paramMap);

    int insertSelective(RestfulLog restfulLog);

    int deleteByPrimaryKey(Long restlogid);

    int updateByPrimaryKeySelective(RestfulLog restfulLog);

    int updateByPrimaryKey(RestfulLog restfulLog);

    RestfulLog selectByPrimaryKey(Long restlogid);
}