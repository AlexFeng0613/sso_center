package com.hsjc.central.domain;

public class ThirdClients {
    private Long id;

    private String clientId;

    private String clientSecret;

    private String contactorname;

    private String contactorphone;

    private String briefname;

    private String ssoPassword;

    private String 3rdPassword;

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
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    public String getContactorname() {
        return contactorname;
    }

    public void setContactorname(String contactorname) {
        this.contactorname = contactorname == null ? null : contactorname.trim();
    }

    public String getContactorphone() {
        return contactorphone;
    }

    public void setContactorphone(String contactorphone) {
        this.contactorphone = contactorphone == null ? null : contactorphone.trim();
    }

    public String getBriefname() {
        return briefname;
    }

    public void setBriefname(String briefname) {
        this.briefname = briefname == null ? null : briefname.trim();
    }

    public String getSsoPassword() {
        return ssoPassword;
    }

    public void setSsoPassword(String ssoPassword) {
        this.ssoPassword = ssoPassword == null ? null : ssoPassword.trim();
    }

    public String get3rdPassword() {
        return 3rdPassword;
    }

    public void set3rdPassword(String 3rdPassword) {
        this.3rdPassword = 3rdPassword == null ? null : 3rdPassword.trim();
    }
}