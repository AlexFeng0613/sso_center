package com.hsjc.central.mapper;

import com.hsjc.central.domain.Organization;

public interface OrganizationMapper {
    int insert(Organization record);

    int insertSelective(Organization record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    Organization selectByPrimaryKey(Long id);
}