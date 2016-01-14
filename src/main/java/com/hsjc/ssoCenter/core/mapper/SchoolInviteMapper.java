package com.hsjc.ssoCenter.core.mapper;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.domain.SchoolInvite;

/**
 * @author : zga
 * @date : 2015-12-10
 * 
 * 学校邀请码Mapper类
 * 
 */
public interface SchoolInviteMapper {
    int insert(SchoolInvite schoolInvite);

    int insertSelective(SchoolInvite schoolInvite);

    int deleteByPrimaryKey(Long inviteid);

    int updateByPrimaryKeySelective(SchoolInvite schoolInvite);

    int updateByPrimaryKey(SchoolInvite schoolInvite);

    int updateUseTimeAndByUserId(SchoolInvite schoolInvite);

    SchoolInvite selectByPrimaryKey(Long inviteid);

    SchoolInvite selectByInviteCode(JSONObject paramJson);
}