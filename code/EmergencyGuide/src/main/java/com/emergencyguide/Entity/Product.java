package com.emergencyguide.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

public class Product {
    private int id;
    private String productName;
    private int productTypeId;
    private String productTypeName;

    private String productUnitName;

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductUnitName() {
        return productUnitName;
    }

    public void setProductUnitName(String productUnitName) {
        this.productUnitName = productUnitName;
    }

    private int productNumber;
    private int productUnitId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productInsertTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date productExpirationTime;
    private String customerOpenId;

    public String getCustomerOpenId() {
        return customerOpenId;
    }

    public void setCustomerOpenId(String customerOpenId) {
        this.customerOpenId = customerOpenId;
    }

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

    public Date getProductInsertTime() {
        return productInsertTime;
    }

    public void setProductInsertTime(Date productInsertTime) {
        this.productInsertTime = productInsertTime;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductExpirationTime() {
        return productExpirationTime;
    }

    public void setProductExpirationTime(Date productExpirationTime) {
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

    public String getProductPercent() {
        return productPercent;
    }

    public void setProductPercent(String productPercent) {
        this.productPercent = productPercent;
    }

    private String remark;
    private String productPhoto;
    private String productPercent;
}
