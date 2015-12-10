package com.hsjc.central.domain;

import java.util.Date;

public class SchoolInvite {
    private Long inviteid;

    private String invitecode;

    private Integer schoolid;

    private Long byuserid;

    private String state;

    private Date createtime;

    private Date usetime;

    public Long getInviteid() {
        return inviteid;
    }

    public void setInviteid(Long inviteid) {
        this.inviteid = inviteid;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode == null ? null : invitecode.trim();
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public Long getByuserid() {
        return byuserid;
    }

    public void setByuserid(Long byuserid) {
        this.byuserid = byuserid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }
}