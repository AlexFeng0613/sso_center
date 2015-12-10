package com.hsjc.central.mapper;

import com.hsjc.central.domain.SsoClientDetails;

public interface ThirdClientsMapper {
    int deleteByPrimaryKey(Long id);


    int insert(SsoClientDetails record);


    int insertSelective(SsoClientDetails record);


    SsoClientDetails selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(SsoClientDetails record);


    int updateByPrimaryKey(SsoClientDetails record);
}