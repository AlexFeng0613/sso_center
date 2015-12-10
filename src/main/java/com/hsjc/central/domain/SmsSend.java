package com.hsjc.central.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class SmsSend implements Serializable {
    private Long id;

    private String phonenum;

    private String msgcontent;

    private Integer sendflag;

    private Date sendtime;

    private String bymodule;

    private Long requestkeyid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public Integer getSendflag() {
        return sendflag;
    }

    public void setSendflag(Integer sendflag) {
        this.sendflag = sendflag;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getBymodule() {
        return bymodule;
    }

    public void setBymodule(String bymodule) {
        this.bymodule = bymodule == null ? null : bymodule.trim();
    }

    public Long getRequestkeyid() {
        return requestkeyid;
    }

    public void setRequestkeyid(Long requestkeyid) {
        this.requestkeyid = requestkeyid;
    }
}