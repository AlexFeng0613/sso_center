package com.hsjc.ssoCenter.core.service;

import com.github.pagehelper.PageHelper;
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
@Service
public class ThirdFilterService {
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
}
