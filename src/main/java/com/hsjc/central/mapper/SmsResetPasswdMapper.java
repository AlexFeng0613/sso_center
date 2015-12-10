package com.hsjc.central.mapper;

import com.hsjc.central.domain.SmsResetPasswd;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface SmsResetPasswdMapper {
    int deleteByPrimaryKey(Integer resetid);

    int insert(SmsResetPasswd record);

    int insertSelective(SmsResetPasswd record);

    SmsResetPasswd selectByPrimaryKey(Integer resetid);

    int updateByPrimaryKeySelective(SmsResetPasswd record);

    int updateByPrimaryKey(SmsResetPasswd record);
}