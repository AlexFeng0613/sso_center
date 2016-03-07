package com.hsjc.ssoCenter.core.service;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.constant.Constant;
import com.hsjc.ssoCenter.core.mapper.SchoolInviteMapper;
import com.hsjc.ssoCenter.core.util.SSOStringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : zga
 * @date : 2015-12-22
 *
 * 邀请码Servcie类
 */
@Service
public class SchoolInviteService extends ApiBaseService {
    private final static Logger logger = Logger.getLogger(SchoolInviteService.class);

    @Autowired
    SchoolInviteMapper schoolInviteMapper;

    /**
     * @author : zga
     * @date : 2016-3-7
     *
     * 查询所有的邀请码
     *
     * @return
     */
    public List<HashMap> selectAllSchoolInvite(){
        return schoolInviteMapper.selectAllSchoolInvite();
    }

    /**
     * @author : zga
     * @date : 2016-3-7
     *
     * 添加相应数量的邀请码
     *
     * @return
     */
    public JSONObject addNewSchoolInvite(JSONObject paramJson){
        JSONObject resultJson = getResultJson();

        try {
            String organizationCode = paramJson.getString("organizationCode");
            Integer addNum = paramJson.getInteger("addNum");

            List addList = new ArrayList<>();
            for(int i = 0;i < addNum;i++){
                HashMap hashMap = new HashMap();
                hashMap.put("schoolId",organizationCode);
                hashMap.put("inviteCode",SSOStringUtil.getRandomString(1,10));
                addList.add(hashMap);
            }

            int num = schoolInviteMapper.batchInsertInviteCode(addList);
            if(num < 0){
                resultJson.put("success",false);
                resultJson.put("message", Constant.BATCH_SCHOOL_INVITE_CODE_FAIL);
                return resultJson;
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }

        resultJson.put("message", Constant.BATCH_SCHOOL_INVITE_CODE_SUCCESS);
        return resultJson;
    }
}
