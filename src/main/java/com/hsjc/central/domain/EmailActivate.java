package com.hsjc.central.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class EmailActivate implements Serializable{
    private Integer activateid;

    private Long userid;

    private Date createtime;

    private Date activetime;

    private String activatekey;

    private String state;

    private Integer validseconds;

    public Integer getActivateid() {
        return activateid;
    }

    public void setActivateid(Integer activateid) {
        this.activateid = activateid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getActivetime() {
        return activetime;
    }

    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

    public String getActivatekey() {
        return activatekey;
    }

    public void setActivatekey(String activatekey) {
        this.activatekey = activatekey == null ? null : activatekey.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getValidseconds() {
        return validseconds;
    }

    public void setValidseconds(Integer validseconds) {
        this.validseconds = validseconds;
    }
}