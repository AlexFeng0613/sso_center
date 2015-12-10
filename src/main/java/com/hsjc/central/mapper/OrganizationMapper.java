package com.hsjc.central.mapper;

import com.hsjc.central.domain.Organization;

/**
 * @author : zga
 * @date : 2015-12-10
 */
public interface OrganizationMapper {
    int insert(Organization record);

    int insertSelective(Organization record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    Organization selectByPrimaryKey(Long id);
}