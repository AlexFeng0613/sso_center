package com.hsjc.central.mapper;

import com.hsjc.central.domain.SchoolInvite;

public interface SchoolInviteMapper {
    int insert(SchoolInvite record);

    int insertSelective(SchoolInvite record);

    int deleteByPrimaryKey(Long inviteid);

    int updateByPrimaryKeySelective(SchoolInvite record);

    int updateByPrimaryKey(SchoolInvite record);

    SchoolInvite selectByPrimaryKey(Long inviteid);
}