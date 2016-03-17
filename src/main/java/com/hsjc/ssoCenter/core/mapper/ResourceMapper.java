package com.hsjc.ssoCenter.core.mapper;

import com.hsjc.ssoCenter.core.domain.Resource;

/**
 * @author : zga
 * @date : 2016-3-17
 *
 * 资源Mapper类
 *
 */
public interface ResourceMapper {
    int insert(Resource record);

    int insertSelective(Resource record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    Resource selectByPrimaryKey(Long id);
}