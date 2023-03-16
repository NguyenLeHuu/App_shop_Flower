package com.example.shoeshop.model;

import com.google.gson.internal.LinkedTreeMap;

public class User {

    private int id;

    private String fullname;
    private boolean gender;
    private String birthday;
    private String image;
    private String password;
    private String address;
    private String phone;
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(int id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public User(int id, String fullname, boolean gender, String birthday, String image, String address, String phone, String username) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.birthday = birthday;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static User convertObjectToUser (Object object){
        LinkedTreeMap<String, Object> objs = (LinkedTreeMap<String, Object>) object;
        User user = new User();
        user.setId(((Double)objs.get("id")).intValue());
        user.setUsername((String)objs.get("username"));
        user.setFullname((String)objs.get("fullname"));
        user.setPassword((String)objs.get("password"));

        return user;
    }
}
