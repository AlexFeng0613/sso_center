package com.hsjc.central.domain;

import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * 消息验证码实体
 */
public class SmsSend {
    private Long id;

    private String phoneNum;

    private String msgContent;

    private Integer sendFlag;

    private Date sendTime;

    private String byModule;

    private Long requestKeyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getByModule() {
        return byModule;
    }

    public void setByModule(String byModule) {
        this.byModule = byModule;
    }

    public Long getRequestKeyId() {
        return requestKeyId;
    }

    public void setRequestKeyId(Long requestKeyId) {
        this.requestKeyId = requestKeyId;
    }
}