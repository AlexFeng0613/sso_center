package com.hsjc.central.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class EmailResetPwd implements Serializable{
    private Integer id;

    private Long userid;

    private Date createtime;

    private Date usetime;

    private String code;

    private String state;

    private Integer validseconds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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