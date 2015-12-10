package com.hsjc.central.domain;

import java.util.Date;

public class SystemLog {
    private Long syslogid;

    private Long userid;

    private Date logintime;

    private String logintype;

    private String loginip;

    private Date createdate;

    public Long getSyslogid() {
        return syslogid;
    }

    public void setSyslogid(Long syslogid) {
        this.syslogid = syslogid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype == null ? null : logintype.trim();
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}