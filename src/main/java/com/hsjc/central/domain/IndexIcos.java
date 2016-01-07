package com.hsjc.central.domain;

/**
 * @author : zga
 * @date : 2016-01-07
 *
 * 主页图标类
 *
 */
public class IndexIcos {
    private Integer id;

    private String iconame;

    private String icopath;

    private String accessurl;

    private String icotype;

    private String icodescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIconame() {
        return iconame;
    }

    public void setIconame(String iconame) {
        this.iconame = iconame;
    }

    public String getIcopath() {
        return icopath;
    }

    public void setIcopath(String icopath) {
        this.icopath = icopath;
    }

    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }

    public String getIcotype() {
        return icotype;
    }

    public void setIcotype(String icotype) {
        this.icotype = icotype;
    }

    public String getIcodescription() {
        return icodescription;
    }

    public void setIcodescription(String icodescription) {
        this.icodescription = icodescription;
    }
}