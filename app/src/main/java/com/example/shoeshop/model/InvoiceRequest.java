package com.example.shoeshop.model;

import java.util.List;

public class InvoiceRequest {
    private String phone;
    private String description;
    private List<ProductToCart> products;

    public InvoiceRequest() {}

    public InvoiceRequest(String phone, String description, List<ProductToCart> products) {
        this.phone = phone;
        this.description = description;
        this.products = products;
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

    public List<ProductToCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductToCart> products) {
        this.products = products;
    }
}
