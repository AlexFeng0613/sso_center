package com.hsjc.central.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class EmailSend implements Serializable{
    private Long id;

    private String email;

    private String subject;

    private Boolean sendflag;

    private Date sendtime;

    private String bymodule;

    private Long requestkeyid;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Boolean getSendflag() {
        return sendflag;
    }

    public void setSendflag(Boolean sendflag) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}