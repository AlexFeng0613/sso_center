package com.hsjc.central.mapper;

import com.hsjc.central.domain.SystemLog;

/**
 * @author : zga
 * @date : 2015-12-10
 */
public interface SystemLogMapper {
    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    int deleteByPrimaryKey(Long syslogid);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    SystemLog selectByPrimaryKey(Long syslogid);
}