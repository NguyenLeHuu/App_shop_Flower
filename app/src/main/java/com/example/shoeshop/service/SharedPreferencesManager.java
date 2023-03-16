package com.example.shoeshop.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shoeshop.model.User;

public class SharedPreferencesManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String ACCESS_TOKEN_KEY = "access_token";

    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";



    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN_KEY, accessToken);
        editor.apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, "");
    }

    public void saveUser(User user){
        editor.putInt(USER_ID,user.getId());
        editor.putString(USER_NAME,user.getFullname());
        editor.apply();
    }

    public User getUser(Context context){
        int id = sharedPreferences.getInt(USER_ID,0);
        String name = sharedPreferences.getString(USER_NAME,"");
        return new User(id,name);
    }

}
