package com.hsjc.central.domain;

import java.io.Serializable;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class SmsResetPasswd implements Serializable {
    private Integer resetid;

    private Integer authid;

    private Long userid;

    private String newpwdplain;

    private String newpwdcrypt;

    public Integer getResetid() {
        return resetid;
    }

    public void setResetid(Integer resetid) {
        this.resetid = resetid;
    }

    public Integer getAuthid() {
        return authid;
    }

    public void setAuthid(Integer authid) {
        this.authid = authid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getNewpwdplain() {
        return newpwdplain;
    }

    public void setNewpwdplain(String newpwdplain) {
        this.newpwdplain = newpwdplain == null ? null : newpwdplain.trim();
    }

    public String getNewpwdcrypt() {
        return newpwdcrypt;
    }

    public void setNewpwdcrypt(String newpwdcrypt) {
        this.newpwdcrypt = newpwdcrypt == null ? null : newpwdcrypt.trim();
    }
}