package com.hsjc.ssoCenter.core.service;

import com.hsjc.ssoCenter.core.domain.Organization;
import com.hsjc.ssoCenter.core.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zga
 * @date : 2016-01-28
 */
@Service
public class OrganizationService {
    @Autowired
    OrganizationMapper organizationMapper;


    /**
     * @author : zga
     * @date : 2016-1-28
     *
     * 查询所有的组织机构
     *
     * @return
     */
    public List<Organization> getAllOrganization(){
        List<Organization> list = organizationMapper.selectAllOrganization();
        return list;
    }

}
