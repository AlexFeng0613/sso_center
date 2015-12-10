package com.hsjc.central.domain;

import java.io.Serializable;

/**
 * @author : zga
 * @date : 2015-12-2
 */
public class UserMain implements Serializable{
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String type;

    private String status;

    private String invitatecode;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getInvitatecode() {
        return invitatecode;
    }

    public void setInvitatecode(String invitatecode) {
        this.invitatecode = invitatecode == null ? null : invitatecode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getCredentialsSalt(){
        return username + salt;
    }
}