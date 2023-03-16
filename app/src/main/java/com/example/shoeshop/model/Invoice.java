package com.example.shoeshop.model;

import android.os.Build;

import com.google.gson.internal.LinkedTreeMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private int id;
    private int userId;
    private String createDate;
    private String phone;
    private String description;
    private double price;

    public Invoice() {}

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

    public static ArrayList<OrderDTO> convertInvoiceToOrderDTO(ArrayList<Invoice> invoices){
        ArrayList<OrderDTO> orders = new ArrayList<>();
        for (Invoice invoice: invoices) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            try {
                Date date = format.parse(invoice.getCreateDate());
                orders.add(new OrderDTO(invoice.getId(), date));
            } catch (ParseException e){
                orders.add(new OrderDTO(invoice.getId(), new Date()));
            }
        }
        return orders;
    }

    public static Invoice convertObjetToInvoice (Object object){
        LinkedTreeMap<String, Object> objs = (LinkedTreeMap<String, Object>) object;
        Invoice invoice = new Invoice();
        invoice.setId(((Double)objs.get("id")).intValue());
        invoice.setPrice(Double.parseDouble((String)objs.get("price")));
        invoice.setDescription((String)objs.get("description"));
        invoice.setCreateDate((String)objs.get("createDate"));
        invoice.setPhone((String)objs.get("phone"));
        return invoice;
    }

    public static ArrayList<Invoice> convertObjectToList (Object object){
        ArrayList<Invoice> invoices = new ArrayList<>();
        ArrayList<Object> list = (ArrayList<Object>)object;
        for (Object data : list) {
            invoices.add(convertObjetToInvoice(data));
        }
        return  invoices;
    }
}
