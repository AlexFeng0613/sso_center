package com.hsjc.ssoCenter.core.mapper;

import com.hsjc.ssoCenter.core.domain.ThirdClients;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<ThirdClients> selectAllThirdClients();

    List<ThirdClients> selectThirdClientByName(@Param("description") String description);
}