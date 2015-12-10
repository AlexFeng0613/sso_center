package com.hsjc.central.mapper;

import com.hsjc.central.domain.ThirdClients;

public interface ThirdClientsMapper {
    int insert(ThirdClients record);

    int insertSelective(ThirdClients record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdClients record);

    int updateByPrimaryKey(ThirdClients record);

    ThirdClients selectByPrimaryKey(Long id);
}