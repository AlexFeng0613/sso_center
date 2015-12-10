package com.hsjc.central.mapper;

import com.hsjc.central.domain.EmailActivate;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface EmailActivateMapper {

    int insert(EmailActivate record);


    int insertSelective(EmailActivate record);


    int deleteByPrimaryKey(Integer activateid);


    int updateByPrimaryKeySelective(EmailActivate record);


    int updateByPrimaryKey(EmailActivate record);


    EmailActivate selectByPrimaryKey(Integer activateid);
}