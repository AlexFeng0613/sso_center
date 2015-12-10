package com.hsjc.central.domain;

import java.io.Serializable;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class SmsBindPhoneNum implements Serializable {
    private Integer bindid;

    private Integer authid;

    private Long userid;

    public Integer getBindid() {
        return bindid;
    }

    public void setBindid(Integer bindid) {
        this.bindid = bindid;
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
}