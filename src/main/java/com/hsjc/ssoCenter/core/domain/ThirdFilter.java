package com.hsjc.ssoCenter.core.domain;

import java.util.Date;

public class ThirdFilter {
    private Integer id;

    private String trdclientid;

    private Integer organizationcode;

    private String tstudent;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrdclientid() {
        return trdclientid;
    }

    public void setTrdclientid(String trdclientid) {
        this.trdclientid = trdclientid;
    }

    public Integer getOrganizationcode() {
        return organizationcode;
    }

    public void setOrganizationcode(Integer organizationcode) {
        this.organizationcode = organizationcode;
    }

    public String getTstudent() {
        return tstudent;
    }

    public void setTstudent(String tstudent) {
        this.tstudent = tstudent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}