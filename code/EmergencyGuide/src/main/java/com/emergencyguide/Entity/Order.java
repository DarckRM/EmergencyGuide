package com.emergencyguide.Entity;

import java.sql.Timestamp;

public class Order {
    private int id;
    private String orderCustomerOpenId;
    private int orderGoodsId;
    private int orderGoodsNumber;
    private Timestamp orderFinishTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCustomerOpenId() {
        return orderCustomerOpenId;
    }

    public void setOrderCustomerOpenId(String orderCustomerOpenId) {
        this.orderCustomerOpenId = orderCustomerOpenId;
    }

    public int getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(int orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public int getOrderGoodsNumber() {
        return orderGoodsNumber;
    }

    public void setOrderGoodsNumber(int orderGoodsNumber) {
        this.orderGoodsNumber = orderGoodsNumber;
    }

    public Timestamp getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Timestamp orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Timestamp getOrderCancelTime() {
        return orderCancelTime;
    }

    public void setOrderCancelTime(Timestamp orderCancelTime) {
        this.orderCancelTime = orderCancelTime;
    }

    public Timestamp getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Timestamp orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private Timestamp orderCancelTime;
    private Timestamp orderCreateTime;
    private String orderStatus;
    private  String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public int getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(int goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public int getGoodsCurrentPrice() {
        return goodsCurrentPrice;
    }

    public void setGoodsCurrentPrice(int goodsCurrentPrice) {
        this.goodsCurrentPrice = goodsCurrentPrice;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public int getOrderWholePrice() {
        return orderWholePrice;
    }

    public void setOrderWholePrice(int orderWholePrice) {
        this.orderWholePrice = orderWholePrice;
    }

    private String goodsDescription;
    private  int goodsOriginalPrice;
    private  int goodsCurrentPrice;
    private  String goodsPhoto;
    private int orderWholePrice;


}
