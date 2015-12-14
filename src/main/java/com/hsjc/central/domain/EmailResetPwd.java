package com.hsjc.central.domain;

import java.util.Date;

/**
 * @author : zga
 * @date : 2015-12-03
 *
 * Email重置密码实体
 */
public class EmailResetPwd {
    private Integer id;

    private Long userId;

    private Date createTime;

    private Date useTime;

    private String code;

    private String state;

    private Integer validSeconds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getValidSeconds() {
        return validSeconds;
    }

    public void setValidSeconds(Integer validSeconds) {
        this.validSeconds = validSeconds;
    }
}