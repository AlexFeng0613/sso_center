package com.hsjc.central.mapper;

import com.hsjc.central.domain.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2015-12-10
 *
 * 同步Mapper
 */
public interface SynMapper {
    int insert(Organization organization);

    int insertSelective(Organization organization);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization organization);

    int updateByPrimaryKey(Organization organization);

    List<HashMap> selectDifferentOrganization(@Param("briefName")String briefName);

    List<HashMap> selectDifferentUser(@Param("briefName")String briefName);

    List<HashMap> selectAllOrganization();

    List<HashMap> selectAllUser();

    Integer countAllOrganization();

    Integer countAllUser();
}