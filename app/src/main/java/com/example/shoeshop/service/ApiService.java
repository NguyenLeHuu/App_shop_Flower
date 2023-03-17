package com.example.shoeshop.service;

import android.util.Log;

import com.example.shoeshop.model.Account;
import com.example.shoeshop.model.Category;
import com.example.shoeshop.model.Flower;
import com.example.shoeshop.model.Invoice;
import com.example.shoeshop.model.InvoiceRequest;
import com.example.shoeshop.model.ProductToCart;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.constants.Constants;
import com.example.shoeshop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    Gson gson = new GsonBuilder()

            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ApiService.class);



    // Cart
    @GET("cart/")
    Call<ResponseModel> listProductsInCart(@Query("userId") int userId,
                                     @Query("page") int page,
                                     @Query("limit") int limit
    );

    @POST("cart/")
    Call<ResponseModel> addProductToCart(@Body ProductToCart productToCart, @Query("userId") int userId);

    @DELETE("cart/{productId}")
    Call<ResponseModel> removeProductToCart(@Path("productId") int productId,
                                            @Query("userId") int userId
                                            );
    @GET("cart/{productId}")
    Call<ResponseModel> getProductInCart(@Path("productId") int productId,
                                            @Query("userId") int userId
    );

    // Category
    @GET("categories/")
    Call<ResponseModel> listCategory(@Query("page") int page,
                              @Query("limit")int limit,
                              @Query("name") String name
    );

    @POST("categories/")
    Call<ResponseModel> createCategory(@Body Category category);

    @PUT("categories/{productId}")
    Call<ResponseModel> updatedCategory(@Body Category category);


    //Favorite
    @GET("favorite/")
    Call<ResponseModel> listProductsFromFavorite(@Query("page") int page,
                                                 @Query("limit")int limit,
                                                 @Query("name") String name
    );

    @POST("favorite/")
    Call<ResponseModel> addProductToFavorite(@Body() Map<String, Object> body);
    //int productId = 1;
    //Map<String, Object> requestBody = new HashMap<>();
    //requestBody.put("productId", productId);


    @PUT("favorite/{productId}")
    Call<ResponseModel> removeProductToFavorite(@Body Category category);


    // Invoice
    @GET("invoices/")
    Call<ResponseModel> listInvoice(@Query("page") int page ,
                              @Query("limit")int limit ,
                              @Query("userId") int userId
                            );

    @POST("invoices/")
    Call<ResponseModel> createInvoice(@Body InvoiceRequest invoiceRequest, @Query("userId") int userId);

    @GET("invoices/{id}")
    Call<ResponseModel> viewInvoiceDetail(@Path("id") int id);

    //Product

    @GET("products/")
    Call<ResponseModel> listProduct(@Query("page") int page,
                              @Query("limit")int limit,
                              @Query("name") String name,
                              @Query("categoryId") int categoryId
                            );

    @GET("products/")
    Call<ResponseModel> listProduct( @Query("limit")int limit);

    @GET("products/{id}")
    Call<ResponseModel> viewProductDetail(@Path("id") int id);

    @Multipart
    @POST("products/")
    Call<ResponseModel> createProduct(@Part("product") Flower flower,
                               @Part MultipartBody.Part[] images);

    @PUT("products/")
    Call<ResponseModel> updateProduct(@Body Flower flower);

    @DELETE("products/{id}")
    Call<ResponseModel> deleteProduct(@Path("id") int id);

    //User
    @GET("users/")
    Call<ResponseModel> listUser(@Query("page") int page ,
                              @Query("limit")int limit,
                              @Query("fullname") String fullname,
                              @Query("id") int id
                            );

    @Multipart
    @POST("users/signup")
    Call<ResponseModel> signUp(@Part("user")User user,
                               @Part MultipartBody.Part image);


    @POST("users/signup")
    Call<ResponseModel> signUp(@Body User user);

    @POST("users/login")
    Call<ResponseModel> login(@Body Account account);

    @PUT("users/")
    Call<ResponseModel> updateUser(@Body User user);

    @GET("users/getUserByAccessToken")
    Call<ResponseModel> getUserByAccessToken (@Header("Authorization") String authHeader);

    @POST("pay/")
    Call<ResponseModel> checkout(@Body Map<String, Object> requestBody);
}
