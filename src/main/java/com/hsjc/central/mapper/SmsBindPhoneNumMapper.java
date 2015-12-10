package com.hsjc.central.mapper;

import com.hsjc.central.domain.SmsBindPhoneNum;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface SmsBindPhoneNumMapper {
    int deleteByPrimaryKey(Integer bindid);

    int insert(SmsBindPhoneNum record);

    int insertSelective(SmsBindPhoneNum record);

    SmsBindPhoneNum selectByPrimaryKey(Integer bindid);

    int updateByPrimaryKeySelective(SmsBindPhoneNum record);

    int updateByPrimaryKey(SmsBindPhoneNum record);
}