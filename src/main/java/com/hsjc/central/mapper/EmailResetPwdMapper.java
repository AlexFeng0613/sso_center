package com.hsjc.central.mapper;

import com.hsjc.central.domain.EmailResetPwd;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface EmailResetPwdMapper {

    int insert(EmailResetPwd record);


    int insertSelective(EmailResetPwd record);


    int deleteByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(EmailResetPwd record);


    int updateByPrimaryKey(EmailResetPwd record);


    EmailResetPwd selectByPrimaryKey(Integer id);
}