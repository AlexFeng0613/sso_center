package com.hsjc.central.mapper;

import com.hsjc.central.domain.EmailSend;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface EmailSendMapper {

    int deleteByPrimaryKey(Long id);


    int insert(EmailSend record);


    int insertSelective(EmailSend record);


    EmailSend selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(EmailSend record);


    int updateByPrimaryKeyWithBLOBs(EmailSend record);


    int updateByPrimaryKey(EmailSend record);
}