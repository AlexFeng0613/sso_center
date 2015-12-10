package com.hsjc.central.mapper;

import com.hsjc.central.domain.SmsResetPasswd;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface SmsResetPasswdMapper {
    int insert(SmsResetPasswd record);

    int insertSelective(SmsResetPasswd record);

    int deleteByPrimaryKey(Integer resetid);

    int updateByPrimaryKeySelective(SmsResetPasswd record);

    int updateByPrimaryKey(SmsResetPasswd record);

    SmsResetPasswd selectByPrimaryKey(Integer resetid);
}