package com.hsjc.central.mapper;

import com.hsjc.central.domain.SmsSend;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface SmsSendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsSend record);

    int insertSelective(SmsSend record);

    SmsSend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsSend record);

    int updateByPrimaryKey(SmsSend record);
}