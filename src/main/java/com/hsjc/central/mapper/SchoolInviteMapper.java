package com.hsjc.central.mapper;

import com.hsjc.central.domain.SchoolInvite;

/**
 * @author : zga
 * @date : 2015-12-10
 */
public interface SchoolInviteMapper {
    int insert(SchoolInvite record);

    int insertSelective(SchoolInvite record);

    int deleteByPrimaryKey(Long inviteid);

    int updateByPrimaryKeySelective(SchoolInvite record);

    int updateByPrimaryKey(SchoolInvite record);

    SchoolInvite selectByPrimaryKey(Long inviteid);
}