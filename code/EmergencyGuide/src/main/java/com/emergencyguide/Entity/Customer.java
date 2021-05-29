package com.emergencyguide.Entity;

import java.util.Date;

/**
 * @author DarckLH
 * @date 2021/5/22 14:19
 * @Description
 */
public class Customer {
    private long id;
    private String openid;
    private String gender;
    private String realname;
    private String nickname;
    private String avatar;
    private String mobilephone;
    private Date registertime;
    private String email;
    private String basicLogo;
    private String subLogo;
    private int profession;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBasicLogo() {
        return basicLogo;
    }

    public void setBasicLogo(String basicLogo) {
        this.basicLogo = basicLogo;
    }

    public String getSubLogo() {
        return subLogo;
    }

    public void setSubLogo(String subLogo) {
        this.subLogo = subLogo;
    }

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", gender='" + gender + '\'' +
                ", realname='" + realname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", registertime=" + registertime +
                ", email='" + email + '\'' +
                ", basicLogo='" + basicLogo + '\'' +
                ", subLogo='" + subLogo + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
