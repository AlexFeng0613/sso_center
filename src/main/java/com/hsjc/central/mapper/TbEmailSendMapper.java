package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbEmailSend;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface TbEmailSendMapper {

    int deleteByPrimaryKey(Long id);


    int insert(TbEmailSend record);


    int insertSelective(TbEmailSend record);


    TbEmailSend selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(TbEmailSend record);


    int updateByPrimaryKeyWithBLOBs(TbEmailSend record);


    int updateByPrimaryKey(TbEmailSend record);
}