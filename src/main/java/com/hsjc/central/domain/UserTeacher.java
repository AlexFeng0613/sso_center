package com.hsjc.central.domain;

import java.io.Serializable;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class UserTeacher implements Serializable {
    private Integer id;

    private String userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}