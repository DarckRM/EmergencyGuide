package com.emergencyguide.Entity;

public class Station {

    private int id;
    //经度信息
    private double longitude;
    //纬度信息
    private double latitude;
    private String openid;
    //补给站类型
    private int type;
    //补给站信息
    private String info;
    //补给站图标
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", openid='" + openid + '\'' +
                ", type=" + type +
                ", info='" + info + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
