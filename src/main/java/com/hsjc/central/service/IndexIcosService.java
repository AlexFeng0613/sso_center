package com.hsjc.central.service;

import com.hsjc.central.mapper.IndexIcosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2016-01-07
 *
 * 主页图标Service类
 *
 */
@Service
public class IndexIcosService {
    @Autowired
    private ApiBaseService apiBaseService;

    @Autowired
    private IndexIcosMapper indexIcosMapper;

    /**
     * @author : zga
     * @date : 2016-01-07
     *
     * 获取所有的主页图标
     *
     * @return
     */
    public List<HashMap> getAllIcos(){
        List<HashMap> list = indexIcosMapper.selectAllIcos();

        return list;
    }
}
