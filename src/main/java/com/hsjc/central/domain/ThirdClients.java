package com.hsjc.central.domain;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * 第三方绑定实体
 */
public class ThirdClients {
    private Long id;

    private String clientId;

    private String clientSecret;

    private String contactorName;

    private String contactorPhone;

    private String briefName;

    private String ssoPassword;

    private String publicKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getContactorPhone() {
        return contactorPhone;
    }

    public void setContactorPhone(String contactorPhone) {
        this.contactorPhone = contactorPhone;
    }

    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    public String getSsoPassword() {
        return ssoPassword;
    }

    public void setSsoPassword(String ssoPassword) {
        this.ssoPassword = ssoPassword;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}