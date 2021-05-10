package com.emergencyguide.Entity;

/**
 * @author DarckLH
 * @date 2021/5/10 15:49
 * @Description 系统信息
 */
public class SystemConfig {

    private int id;
    private int paytime;
    private int autofinishtime;
    private String servicephone;
    private String refundphone;
    private int ordercanceltime;
    private int msgcount;
    private int msgtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaytime() {
        return paytime;
    }

    public void setPaytime(int paytime) {
        this.paytime = paytime;
    }

    public int getAutofinishtime() {
        return autofinishtime;
    }

    public void setAutofinishtime(int autofinishtime) {
        this.autofinishtime = autofinishtime;
    }

    public String getServicephone() {
        return servicephone;
    }

    public void setServicephone(String servicephone) {
        this.servicephone = servicephone;
    }

    public String getRefundphone() {
        return refundphone;
    }

    public void setRefundphone(String refundphone) {
        this.refundphone = refundphone;
    }

    public int getOrdercanceltime() {
        return ordercanceltime;
    }

    public void setOrdercanceltime(int ordercanceltime) {
        this.ordercanceltime = ordercanceltime;
    }

    public int getMsgcount() {
        return msgcount;
    }

    public void setMsgcount(int msgcount) {
        this.msgcount = msgcount;
    }

    public int getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(int msgtime) {
        this.msgtime = msgtime;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
                "id=" + id +
                ", paytime=" + paytime +
                ", autofinishtime=" + autofinishtime +
                ", servicephone='" + servicephone + '\'' +
                ", refundphone='" + refundphone + '\'' +
                ", ordercanceltime=" + ordercanceltime +
                ", msgcount=" + msgcount +
                ", msgtime=" + msgtime +
                '}';
    }
}
