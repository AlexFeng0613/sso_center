package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbEmailActivate;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface TbEmailActivateMapper {

    int deleteByPrimaryKey(Integer activateid);


    int insert(TbEmailActivate record);


    int insertSelective(TbEmailActivate record);


    TbEmailActivate selectByPrimaryKey(Integer activateid);


    int updateByPrimaryKeySelective(TbEmailActivate record);


    int updateByPrimaryKey(TbEmailActivate record);
}