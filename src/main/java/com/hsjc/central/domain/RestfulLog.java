package com.hsjc.central.domain;

import java.util.Date;

public class RestfulLog {
    private Long restlogid;

    private Long userid;

    private Integer actionid;

    private Integer respcode;

    private String description;

    private Date actiontime;

    public Long getRestlogid() {
        return restlogid;
    }

    public void setRestlogid(Long restlogid) {
        this.restlogid = restlogid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getActionid() {
        return actionid;
    }

    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }

    public Integer getRespcode() {
        return respcode;
    }

    public void setRespcode(Integer respcode) {
        this.respcode = respcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getActiontime() {
        return actiontime;
    }

    public void setActiontime(Date actiontime) {
        this.actiontime = actiontime;
    }
}