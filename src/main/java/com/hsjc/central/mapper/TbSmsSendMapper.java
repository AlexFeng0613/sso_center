package com.hsjc.central.mapper;

import com.hsjc.central.domain.TbSmsSend;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public interface TbSmsSendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbSmsSend record);

    int insertSelective(TbSmsSend record);

    TbSmsSend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbSmsSend record);

    int updateByPrimaryKey(TbSmsSend record);
}