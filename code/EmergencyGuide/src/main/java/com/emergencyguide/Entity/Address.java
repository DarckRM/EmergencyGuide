package com.emergencyguide.Entity;

/**
 * @author DarckLH
 * @date 2021/5/30 16:41
 * @Description
 */
public class Address {

    private int id;
    private String openid;
    private String recipient;
    private String mobilephone;
    private String address;
    private String isdefault;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", recipient='" + recipient + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", address='" + address + '\'' +
                ", isdefault='" + isdefault + '\'' +
                '}';
    }
}
