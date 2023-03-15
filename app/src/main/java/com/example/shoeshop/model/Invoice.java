package com.example.shoeshop.model;

public class Invoice {
    private int id;
    private int userId;
    private String createDate;
    private String phone;
    private String description;
    private double price;

    public Invoice(int id, int userId, String createDate, String phone, String description, double price) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.phone = phone;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
