package com.example.shoeshop.model;


//import com.example.androidflower.R;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class Flower {

    private int id;
    private String image;
    private String name;
    private double price;
    private int status;
    private int quantity;
    private String description;

    public Flower() {}

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

    public Flower(String image, String name) {
        this.image = image;
        this.name = name;
        this.price = 0;
    }

    public Flower(int id , String image, String name, double price) {
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

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public static ArrayList<CartItemDTO> convertListFlowerToCartItem(ArrayList<Flower> flowers){
        ArrayList<CartItemDTO> lists = new ArrayList<>();
        for (Flower flower: flowers) {
            lists.add(convertFlowerToCartItem(flower));
        }
        return lists;
    }

    public static CartItemDTO convertFlowerToCartItem(Flower flower){
        CartItemDTO dto = new CartItemDTO();
        dto.setId(flower.getId());
        dto.setName(flower.getName());
        dto.setQuantity(flower.getQuantity());
        dto.setPrice(flower.getPrice());
        dto.setImage(flower.getImage());
        return dto;
    }

    public static  ShoesDTO convertFlowerToShoesDto(Flower flower){
        ShoesDTO dto = new ShoesDTO();
        dto.setId(flower.getId());
        dto.setImage(flower.getImage());
        dto.setName(flower.getName());
        dto.setPrice(flower.getPrice());
        dto.setQuantity(flower.getQuantity());
        dto.setDescription(flower.getDescription());
        return dto;
    }

    public static ArrayList<ShoesDTO> convertListFlowerToShoesItem(ArrayList<Flower> flowers){
        ArrayList<ShoesDTO> lists = new ArrayList<>();
        for (Flower flower: flowers) {
            lists.add(convertFlowerToShoesDto(flower));
        }
        return lists;
    }

    public static Flower convertObjetToFlower (Object object){
        LinkedTreeMap<String, Object> objs = (LinkedTreeMap<String, Object>) object;
        Flower flower = new Flower();
        flower.setId(((Double)objs.get("id")).intValue());
        flower.setPrice(Double.parseDouble((String)objs.get("price")));
        flower.setDescription((String)objs.get("description"));
        flower.setQuantity(((Double)objs.get("quantity")).intValue());
        flower.setImage((String)objs.get("image"));
        flower.setName((String)objs.get("name"));

        return flower;
    }

    public static ArrayList<Flower> convertObjectToList (Object object){
        ArrayList<Flower> flowers = new ArrayList<>();
        ArrayList<Object> list = (ArrayList<Object>)object;
        for (Object data : list) {
            flowers.add(convertObjetToFlower(data));
        }
        return  flowers;
    }
}
