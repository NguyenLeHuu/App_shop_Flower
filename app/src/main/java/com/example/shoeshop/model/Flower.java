package com.example.shoeshop.model;


//import com.example.androidflower.R;

import java.util.ArrayList;
import java.util.List;

public class Flower {

    private int id;
    private int image;
    private String name;
    private double price;
    private int status;
    private int quantity;
    private String description;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Flower(int image, String name) {
        this.image = image;
        this.name = name;
        this.price = 0;
    }

    public Flower(int id , int image, String name, double price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public static List<Flower> getListFlower(){
//        List<Flower> list = new ArrayList<>();
//        list.add(new Flower(R.drawable.hoacamtu,"Hoa cẩm tú"));
//        list.add(new Flower(R.drawable.hoahong,"Hoa hồng"));
//        list.add(new Flower(R.drawable.hoasen,"Hoa sen"));
//        list.add(new Flower(R.drawable.hoatra,"Hoa trà"));
//        list.add(new Flower(R.drawable.hoahuongduong,"Hoa huóng dương"));
//
//        list.add(new Flower(R.drawable.hoacamtu,"Hoa cẩm tú"));
//        list.add(new Flower(R.drawable.hoahong,"Hoa hồng"));
//        list.add(new Flower(R.drawable.hoasen,"Hoa sen"));
//        list.add(new Flower(R.drawable.hoatra,"Hoa trà"));
//        list.add(new Flower(R.drawable.hoahuongduong,"Hoa huóng dương"));
//
//        list.add(new Flower(R.drawable.hoacamtu,"Hoa cẩm tú"));
//        list.add(new Flower(R.drawable.hoahong,"Hoa hồng"));
//        list.add(new Flower(R.drawable.hoasen,"Hoa sen"));
//        list.add(new Flower(R.drawable.hoatra,"Hoa trà"));
//        list.add(new Flower(R.drawable.hoahuongduong,"Hoa huóng dương"));
//
//        return list;
//    }
}
