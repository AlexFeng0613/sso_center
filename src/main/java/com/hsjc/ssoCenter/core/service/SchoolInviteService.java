package com.hsjc.ssoCenter.core.service;

import com.hsjc.ssoCenter.core.mapper.SchoolInviteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015-12-22
 *
 * 邀请码Servcie类
 */
@Service
public class SchoolInviteService extends ApiBaseService {
    @Autowired
    private SchoolInviteMapper schoolInviteMapper;


}
