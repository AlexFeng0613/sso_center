package com.hsjc.central.mapper;

import com.hsjc.central.domain.ThirdClients;

/**
 * @author : zga
 * @date : 2015-12-10
 */
public interface ThirdClientsMapper {
    int insert(ThirdClients thirdClients);

    int insertSelective(ThirdClients thirdClients);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdClients thirdClients);

    int updateByPrimaryKey(ThirdClients thirdClients);

    ThirdClients selectByPrimaryKey(Long id);

    ThirdClients selectByClientId(ThirdClients thirdClients);
}