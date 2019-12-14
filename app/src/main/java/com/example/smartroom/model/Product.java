package com.example.smartroom.model;

public class Product {
    String productId;
    String valueName;
    String data;
    String time;

    public Product(String productId, String valueName, String data, String time) {
        this.productId = productId;
        this.valueName = valueName;
        this.data = data;
        this.time = time;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
