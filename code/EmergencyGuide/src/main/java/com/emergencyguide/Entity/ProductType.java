package com.emergencyguide.Entity;

public class ProductType {
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String  productTypeName;
    private  String remark;

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int  productShelfLifeValue;

    private int productRichnessValue;

    public int getProductShelfLifeValue() {
        return productShelfLifeValue;
    }

    public void setProductShelfLifeValue(int productShelfLifeValue) {
        this.productShelfLifeValue = productShelfLifeValue;
    }

    public int getProductRichnessValue() {
        return productRichnessValue;
    }

    public void setProductRichnessValue(int productRichnessValue) {
        this.productRichnessValue = productRichnessValue;
    }
}
