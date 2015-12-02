package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbEmailResetPwd;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface TbEmailResetPwdMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(TbEmailResetPwd record);


    int insertSelective(TbEmailResetPwd record);


    TbEmailResetPwd selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(TbEmailResetPwd record);


    int updateByPrimaryKey(TbEmailResetPwd record);
}