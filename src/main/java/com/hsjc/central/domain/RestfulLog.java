package com.hsjc.central.domain;

import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * 接口同步日志实体
 */
public class RestfulLog {
    private Long restLogId;

    private Long userId;

    private Integer actionId;

    private Integer respCode;

    private String description;

    private Date actionTime;

    public Long getRestLogId() {
        return restLogId;
    }

    public void setRestLogId(Long restLogId) {
        this.restLogId = restLogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}