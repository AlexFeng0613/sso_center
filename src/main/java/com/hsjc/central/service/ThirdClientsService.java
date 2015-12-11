package com.hsjc.central.service;

import com.hsjc.central.domain.ThirdClients;
import com.hsjc.central.mapper.ThirdClientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zga
 * @date : 2015-12-11
 *
 * 第三方Service类
 */
@Service
public class ThirdClientsService {
    @Autowired
    private ThirdClientsMapper thirdClientsMapper;

    /**
     * @author : zga
     * @date : 2015-12-11
     *
     * 根据clientid查询记录
     * @param thirdClients
     * @return
     */
    public ThirdClients selectByClientId(ThirdClients thirdClients){
        return  thirdClientsMapper.selectByClientId(thirdClients);
    }

}
