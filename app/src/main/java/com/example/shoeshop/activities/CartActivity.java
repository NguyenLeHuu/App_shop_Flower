package com.example.shoeshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoeshop.R;
import com.example.shoeshop.adapters.CartAdapter;
import com.example.shoeshop.constants.Constants;
import com.example.shoeshop.model.CartItemDTO;
import com.example.shoeshop.model.Flower;
import com.example.shoeshop.model.InvoiceRequest;
import com.example.shoeshop.model.OrderDTO;
import com.example.shoeshop.model.ProductToCart;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.model.User;
import com.example.shoeshop.service.ApiService;
import com.example.shoeshop.service.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private ListView listCartView;
    JsonObject data = new JsonObject();
    JsonArray products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setTitle("Your Cart");


        listCartView = findViewById(R.id.listCartView);

        ArrayList<Flower> listFlower = new ArrayList<>();


        SharedPreferencesManager spm = new SharedPreferencesManager(CartActivity.this);
        User user = spm.getUser(CartActivity.this);

        ApiService.apiService.listProductsInCart(user.getId(),1,20).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                for (Flower data : Flower.convertObjectToList(responseModel.getData())) {
                   listFlower.add(data);
                }
                CartAdapter adapter = new CartAdapter();
                adapter.setCartDTOList(Flower.convertListFlowerToCartItem(listFlower));
                listCartView.setAdapter(adapter);

                switch (response.code()){
                    case 400:
                        Toast.makeText(CartActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                        break;
                    case 200:
                        Object data_res_model = response.body().getData();
                        Gson gson = new Gson();
                        String dataString = gson.toJson(data_res_model);
                        products = JsonParser.parseString(dataString).getAsJsonArray();
                        if(!products.isEmpty()){
                            Toast.makeText(CartActivity.this,"You have some product in cart",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CartActivity.this,"Nothing in cart",Toast.LENGTH_SHORT).show();
                        }

                        data.add("products",products);
                        data.addProperty("phone","0354187011");
                        data.addProperty("description","mua hàng giá rẻ");
                        Log.e("data", data.toString());
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(CartActivity.this,"Failed",Toast.LENGTH_SHORT);
            }
        });

//        CartAdapter adapter = new CartAdapter();
//        CartDAO dao = new CartDAO(this);
//        try {
//            ArrayList<CartItemDTO> cartItems = dao.getCartItems();
//            adapter.setCartDTOList(cartItems);
//            listCartView.setAdapter(adapter);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    public void clickToCheckout(View view) {
        try {
            if(!products.isEmpty()){
                SharedPreferencesManager spm = new SharedPreferencesManager(CartActivity.this);
                User user = spm.getUser(CartActivity.this);
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("userId", user.getId());
                requestBody.put("data", data);

                ApiService.apiService.checkout(requestBody).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        switch (response.code()){
                            case 400:
                                Toast.makeText(CartActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                                break;
                            case 200:
                                Toast.makeText(CartActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                                String approvalUrl = response.body().getMessage();
                                if (approvalUrl != null) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(approvalUrl));
                                    startActivity(intent);
                                }
                                break;
                            default:
                                Toast.makeText(CartActivity.this,"Failed api checkout",Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.e("Failure","Failure");
                    }
                });
            }else{
                Toast.makeText(CartActivity.this,"Nothing in cart",Toast.LENGTH_SHORT).show();

            }


        } catch (Exception e) {
            Log.e("CHECKOUT",e.toString());
        }
            //Code cũ của Huy
//            CartAdapter adapter = (CartAdapter) listCartView.getAdapter();
//            ArrayList<CartItemDTO> list = adapter.getCartDTOList();
//
//            SharedPreferencesManager spm = new SharedPreferencesManager(CartActivity.this);
//            User user = spm.getUser(CartActivity.this);
//
//            InvoiceRequest invoiceRequest = new InvoiceRequest();
//            invoiceRequest.setDescription("");
//            invoiceRequest.setPhone("");
//            invoiceRequest.setProducts(ProductToCart.convertListCartItemDTOToThis(list));
//
//            ApiService.apiService.createInvoice(invoiceRequest,user.getId()).enqueue(new Callback<ResponseModel>() {
//                @Override
//                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                    Log.e("Create Invoice",response.toString());
//                    Toast.makeText(CartActivity.this, "Create Invoice Successful", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<ResponseModel> call, Throwable t) {
//                    Log.e("Create Invoice","Failed");
//                    Toast.makeText(CartActivity.this, "Create Invoice Failed", Toast.LENGTH_LONG).show();
//                }
//            });



//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    public void clickToCheckout(View view) {
//        try {
//            CartAdapter adapter = (CartAdapter) listCartView.getAdapter();
//            ArrayList<CartItemDTO> list = adapter.getCartDTOList();
//            OrderDAO dao = new OrderDAO(this);
//
//            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
//            String userId = sharedPreferences.getString("IDPref", null);
//            OrderDTO dto = new OrderDTO(new Date(), userId);
//            long orderId = dao.create(dto);
//            if (orderId < 0) {
//                Toast.makeText(this, "Failed to create order!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            boolean success = dao.addOrderDetail(orderId, list);
//            if (success) {
//                CartDAO cartDAO = new CartDAO(this);
//                cartDAO.clearCart();
//
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}