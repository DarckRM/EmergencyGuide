package com.emergencyguide.Entity;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String productName;
    private int productTypeId;
    private int productNumber;
    private int productUnitId;
    private Timestamp productInsertTime;
    private Timestamp productCreateTime;
    private Timestamp productExpirationTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getProductUnitId() {
        return productUnitId;
    }

    public void setProductUnitId(int productUnitId) {
        this.productUnitId = productUnitId;
    }

    public Timestamp getProductInsertTime() {
        return productInsertTime;
    }

    public void setProductInsertTime(Timestamp productInsertTime) {
        this.productInsertTime = productInsertTime;
    }

    public Timestamp getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Timestamp productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Timestamp getProductExpirationTime() {
        return productExpirationTime;
    }

    public void setProductExpirationTime(Timestamp productExpirationTime) {
        this.productExpirationTime = productExpirationTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    private String remark;
    private String productPhoto;
}
