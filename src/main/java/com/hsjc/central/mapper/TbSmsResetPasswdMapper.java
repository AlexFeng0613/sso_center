package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbSmsResetPasswd;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface TbSmsResetPasswdMapper {
    int deleteByPrimaryKey(Integer resetid);

    int insert(TbSmsResetPasswd record);

    int insertSelective(TbSmsResetPasswd record);

    TbSmsResetPasswd selectByPrimaryKey(Integer resetid);

    int updateByPrimaryKeySelective(TbSmsResetPasswd record);

    int updateByPrimaryKey(TbSmsResetPasswd record);
}