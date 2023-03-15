package com.example.shoeshop.api;

import com.example.shoeshop.DTO.UserDTO;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://example.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @GET("users")
    Call<List<UserDTO>> getAllUsers(@Query("page") int page,
                                    @Query("limit") int limit,
                                    @Query("fullname") int fullname,
                                    @Query("id") int id);




}