package com.hsjc.central.domain;

public class Restactiontype {
    private Long id;

    private Integer actionid;

    private String actionname;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionid() {
        return actionid;
    }

    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname == null ? null : actionname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}