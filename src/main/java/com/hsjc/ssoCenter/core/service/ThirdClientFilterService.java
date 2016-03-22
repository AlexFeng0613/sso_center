package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hsjc.ssoCenter.core.domain.ThirdFilter;
import com.hsjc.ssoCenter.core.mapper.ThirdFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2016-01-26
 *
 * 第三方导入数据过滤列表
 *
 */
@SuppressWarnings("ALL")
@Service
public class ThirdClientFilterService extends ApiBaseService{
    @Autowired
    ThirdFilterMapper thirdFilterMapper;

    /**
     * @author : zga
     * @date : 2016-1-26
     *
     * 查询所有的第三方导入数据过滤列表
     *
     */
    public List<HashMap> selectAllThirdFilters(String description,Integer currentPage,Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        return thirdFilterMapper.selectAllThirdFilters();
    }


    /**
     * @author : zga
     * @date : 2016-1-28
     *
     * 新增第三方过滤
     *
     * @param paramJson
     * @return
     */
    public JSONObject addNewThirdClientFilter(JSONObject paramJson){
        String clientId = paramJson.getString("clientId");
        Integer organizatinCode = paramJson.getInteger("organizationCode");
        String teacherOrStudent = paramJson.getString("tstudent");

        ThirdFilter thirdFilter = new ThirdFilter();
        thirdFilter.setTrdclientid(clientId);
        thirdFilter.setOrganizationcode(organizatinCode);
        thirdFilter.setTstudent(teacherOrStudent);

        try{
            thirdFilterMapper.insert(thirdFilter);
            return validate(1);
        } catch (Exception e){
            return validate(0);
        }
    }

    /**
     * @author : zga
     * @date : 2016-3-22
     *
     * 新增第三方过滤
     *
     * @param paramJson
     * @return
     */
    public JSONObject deleteThirdClientFilter(JSONObject paramJson){
        Integer id = paramJson.getInteger("filterId");
        try {
            thirdFilterMapper.deleteByPrimaryKey(id);
            return validate(1);
        } catch (Exception e) {
            return validate(0);
        }
    }
}
