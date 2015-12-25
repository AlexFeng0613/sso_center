package com.hsjc.central.mapper;

import com.hsjc.central.domain.SmsSend;

/**
 * @author : zga
 * @date : 2015-12-2
 *
 * SMS Mapper类
 *
 */
public interface SmsSendMapper {
    int insert(SmsSend smsSend);

    int insertSelective(SmsSend smsSend);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsSend smsSend);

    int updateByPrimaryKey(SmsSend smsSend);

    SmsSend selectByPrimaryKey(Long id);
}