package com.emergencyguide.Entity;

public class Goods {
    private  int id;
    private String goodsDescription;
    private int goodsOriginalPrice;
    private int goodsCurrentPrice;
    private String goodsPhoto;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    private String goodsName;

    public int getId() {
        return id;
    }

    public int getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(int goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
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
}
