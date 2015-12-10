package com.hsjc.central.mapper;

import com.hsjc.central.domain.SmsBindPhoneNum;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface SmsBindPhoneNumMapper {
    int insert(SmsBindPhoneNum record);

    int insertSelective(SmsBindPhoneNum record);

    int deleteByPrimaryKey(Integer bindid);

    int updateByPrimaryKeySelective(SmsBindPhoneNum record);

    int updateByPrimaryKey(SmsBindPhoneNum record);

    SmsBindPhoneNum selectByPrimaryKey(Integer bindid);
}