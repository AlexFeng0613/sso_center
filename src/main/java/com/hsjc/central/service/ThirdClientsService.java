package com.hsjc.central.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hsjc.central.constant.Constant;
import com.hsjc.central.domain.ThirdClients;
import com.hsjc.central.mapper.SynMapper;
import com.hsjc.central.mapper.ThirdClientsMapper;
import com.hsjc.central.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2015-12-11
 *
 * 第三方Service类
 */
@SuppressWarnings("ALL")
@Service
public class ThirdClientsService {
    @Autowired
    private ThirdClientsMapper thirdClientsMapper;

    @Autowired
    private SynMapper synMapper;

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

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步所有组织机构
     *
     * @param paramJson
     * @return
     */
    public JSONObject getAllOrganization(JSONObject paramJson){
        JSONObject resJsonObject = new JSONObject();

        boolean flag = validateClientId(paramJson);
        if(!flag){
            resJsonObject.put("flag",false);
            resJsonObject.put("message","client_id not exists or password is error!");
            return resJsonObject;
        }

        Integer currentPage = paramJson.getInteger("currentPage");
        Integer pageSize  =paramJson.getInteger("pageSize");

        if(currentPage == null || currentPage == 0) currentPage = 1;
        if(pageSize == null || pageSize == 0) currentPage = 600;

        PageHelper.startPage(currentPage,pageSize);
        List<HashMap> organizationList = synMapper.selectAllOrganization();

        Integer totalNum = synMapper.countAllOrganization();
        Integer leftNum = totalNum - currentPage * pageSize;

        resJsonObject.put("organization",organizationList);
        resJsonObject.put("leftNum",leftNum);

        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步增量组织机构
     *
     * @param paramJson
     * @return
     */
    public JSONObject getDifferentOrganization(JSONObject paramJson){
        JSONObject resJsonObject = new JSONObject();

        boolean flag = validateClientId(paramJson);
        if(!flag){
            resJsonObject.put("flag",false);
            resJsonObject.put("message","client_id not exists or password is error!");
            return resJsonObject;
        }


        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);
        if(thirdClients == null) return null;
        List<HashMap> organizationList = synMapper.selectDifferentOrganization(thirdClients.getBriefName());

        resJsonObject.put("organization",organizationList);

        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步所有用户
     *
     * @param paramJson
     * @return
     */
    public JSONObject getAllUser(JSONObject paramJson){
        JSONObject resJsonObject = new JSONObject();

        boolean flag = validateClientId(paramJson);
        if(!flag){
            resJsonObject.put("flag",false);
            resJsonObject.put("message","client_id not exists or password is error!");
            return resJsonObject;
        }

        Integer currentPage = paramJson.getInteger("currentPage");
        Integer pageSize  =paramJson.getInteger("pageSize");

        if(currentPage == null || currentPage == 0) currentPage = 1;
        if(pageSize == null || pageSize == 0) currentPage = 600;

        PageHelper.startPage(currentPage,pageSize);

        PageHelper.startPage(currentPage,pageSize);
        List<HashMap> userList = synMapper.selectAllUser();

        Integer totalNum = synMapper.countAllUser();
        Integer leftNum = totalNum - currentPage * pageSize;

        resJsonObject.put("user",userList);
        resJsonObject.put("leftNum",leftNum);

        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 同步增量用户
     *
     * @param paramJson
     * @return
     */
    public JSONObject getDifferentUser(JSONObject paramJson){
        JSONObject resJsonObject = new JSONObject();

        boolean flag = validateClientId(paramJson);
        if(!flag){
            resJsonObject.put("flag",false);
            resJsonObject.put("message","client_id not exists or password is error!");
            return resJsonObject;
        }

        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);
        List<HashMap> userList = synMapper.selectDifferentUser(thirdClients.getBriefName());
        resJsonObject.put("user", userList);

        return resJsonObject;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 验证client_id
     *
     * @param paramJson
     * @return
     */
    public boolean validateClientId(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        if(thirdClients == null) return false;

        String client_secret = thirdClients.getClientSecret();
        String password = paramJson.getString("password");
        String time = paramJson.getString("time");
        String validatePwd = MD5Util.encode(client_secret + MD5Util.encode(Constant.public_key) + time);
        if(!validatePwd.equals(password)) return false;

        return true;
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 根据ClientId查询第三方绑定表
     *
     * @param paramJson
     * @return
     */
    public ThirdClients getThirdClientsByClientId(JSONObject paramJson){
        String clientId = paramJson.getString("clientId");


        ThirdClients paramThirdClients = new ThirdClients();
        paramThirdClients.setClientId(clientId);
        ThirdClients thirdClients = thirdClientsMapper.selectByClientId(paramThirdClients);

        return thirdClients;
    }
}
