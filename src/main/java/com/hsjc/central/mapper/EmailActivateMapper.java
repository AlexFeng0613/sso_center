package com.hsjc.central.mapper;

import com.hsjc.central.domain.EmailActivate;

/**
 * @author : zga
 * @date : 2015-12-2
 *
 * Email激活Mapper类
 *
 */
public interface EmailActivateMapper {

    int insert(EmailActivate emailActivate);


    int insertSelective(EmailActivate emailActivate);


    int deleteByPrimaryKey(Integer activateid);


    int updateByPrimaryKeySelective(EmailActivate emailActivate);


    int updateByPrimaryKey(EmailActivate emailActivate);


    EmailActivate selectByPrimaryKey(Integer activateid);
}