package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbSmsBindPhoneNum;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface TbSmsBindPhoneNumMapper {
    int deleteByPrimaryKey(Integer bindid);

    int insert(TbSmsBindPhoneNum record);

    int insertSelective(TbSmsBindPhoneNum record);

    TbSmsBindPhoneNum selectByPrimaryKey(Integer bindid);

    int updateByPrimaryKeySelective(TbSmsBindPhoneNum record);

    int updateByPrimaryKey(TbSmsBindPhoneNum record);
}