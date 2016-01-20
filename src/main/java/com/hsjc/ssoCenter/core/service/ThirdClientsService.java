package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hsjc.ssoCenter.core.annotation.SSOSystemLog;
import com.hsjc.ssoCenter.core.constant.ThirdSynConstant;
import com.hsjc.ssoCenter.core.domain.ThirdClients;
import com.hsjc.ssoCenter.core.mapper.SynMapper;
import com.hsjc.ssoCenter.core.mapper.ThirdClientsMapper;
import com.hsjc.ssoCenter.core.mapper.ThirdFilterMapper;
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
public class ThirdClientsService extends ApiBaseService{
    @Autowired
    private ThirdClientsMapper thirdClientsMapper;

    @Autowired
    private SynMapper synMapper;

    @Autowired
    private ThirdFilterMapper thirdFilterMapper;

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
    @SSOSystemLog(actionId = 2,description = "同步所有组织机构",module = "接口同步")
    public JSONObject getAllOrganization(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);

        if(!resultJson.getBoolean("flag")) return resultJson;

        Integer currentPage = paramJson.getInteger("currentPage");
        Integer pageSize  =paramJson.getInteger("pageSize");

        if(currentPage == null || currentPage == 0) currentPage = 1;
        if(pageSize == null || pageSize == 0) currentPage = 600;

        try {
            /**
             * 查询第三方过滤表
             */
            HashMap paramMap = new HashMap();
            paramMap.put("trdClientId",paramJson.getString("clientId"));
            PageHelper.startPage(currentPage, pageSize);
            List<HashMap> organizationList = synMapper.selectAllOrganization(paramMap);

            if(organizationList == null || (organizationList != null && organizationList.size() < 1)){
                resultJson.put("flag",false);
                resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                return resultJson;
            }

            Integer totalNum = synMapper.countAllOrganization();
            Integer leftNum = totalNum - currentPage * pageSize;

            resultJson.put("organization", organizationList);
            resultJson.put("leftNum", leftNum);
        }catch (Exception e){
            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        return resultJson;
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
    @SSOSystemLog(actionId = 3,description = "同步增量组织机构",module = "接口同步")
    public JSONObject getDifferentOrganization(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);

        if(!resultJson.getBoolean("flag")) return resultJson;

        try{
            List<HashMap> organizationList = synMapper.selectDifferentOrganization(thirdClients.getBriefName());

            if(organizationList == null || (organizationList!= null && organizationList.size() < 1)){
                resultJson.put("flag",false);
                resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                return resultJson;
            }
            resultJson.put("organization",organizationList);
        } catch (Exception e){
            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        return resultJson;
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
    @SSOSystemLog(actionId = 4,description = "同步所有用户",module = "接口同步")
    public JSONObject getAllUser(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);

        if(!resultJson.getBoolean("flag")) return resultJson;

        Integer currentPage = paramJson.getInteger("currentPage");
        Integer pageSize  =paramJson.getInteger("pageSize");

        if(currentPage == null || currentPage == 0) currentPage = 1;
        if(pageSize == null || pageSize == 0) currentPage = 600;

        try{
            HashMap paramMap = new HashMap();
            paramMap.put("trdClientId",paramJson.getString("clientId"));

            PageHelper.startPage(currentPage,pageSize);
            List<HashMap> userList = synMapper.selectAllUser(paramMap);

            Integer totalNum = synMapper.countAllUser();

            if(userList == null || (userList!= null && userList.size() < 1)){
                resultJson.put("flag",false);
                resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                return resultJson;
            }

            Integer leftNum = totalNum - currentPage * pageSize;

            resultJson.put("user",userList);
            resultJson.put("leftNum",leftNum);
        } catch (Exception e){
            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        return resultJson;
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
    @SSOSystemLog(actionId = 5,description = "同步增量用户",module = "接口同步")
    public JSONObject getDifferentUser(JSONObject paramJson){
        ThirdClients thirdClients = getThirdClientsByClientId(paramJson);

        JSONObject resultJson = validateClientIdAndPassword(paramJson,thirdClients);

        if(!resultJson.getBoolean("flag")) return resultJson;
        try {
            List<HashMap> userList = synMapper.selectDifferentUser(thirdClients.getBriefName());

            if(userList == null || (userList != null && userList.size() < 1)){
                resultJson.put("flag",false);
                resultJson.put("respCode", ThirdSynConstant.NO_SYN_DATA);
                return resultJson;
            }

            resultJson.put("user", userList);
        } catch (Exception e) {
            resultJson.put("flag",false);
            resultJson.put("respCode", ThirdSynConstant.SERVER_EXPECTION);
            return resultJson;
        }

        resultJson.put("respCode",ThirdSynConstant.SYN_SUCCESS);
        return resultJson;
    }
}
