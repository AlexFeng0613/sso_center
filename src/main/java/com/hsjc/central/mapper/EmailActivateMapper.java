package com.hsjc.central.mapper;

import com.hsjc.central.domain.EmailActivate;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface EmailActivateMapper {

    int deleteByPrimaryKey(Integer activateid);


    int insert(EmailActivate record);


    int insertSelective(EmailActivate record);


    EmailActivate selectByPrimaryKey(Integer activateid);


    int updateByPrimaryKeySelective(EmailActivate record);


    int updateByPrimaryKey(EmailActivate record);
}