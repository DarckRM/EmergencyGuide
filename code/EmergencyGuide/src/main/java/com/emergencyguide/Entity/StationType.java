package com.emergencyguide.Entity;

public class StationType {

    private int id;
    private String typename;
    private String icon;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "StationType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                ", icon='" + icon + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
