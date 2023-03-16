package com.example.shoeshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private ListView listCartView;

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
            CartAdapter adapter = (CartAdapter) listCartView.getAdapter();
            ArrayList<CartItemDTO> list = adapter.getCartDTOList();

            SharedPreferencesManager spm = new SharedPreferencesManager(CartActivity.this);
            User user = spm.getUser(CartActivity.this);

            InvoiceRequest invoiceRequest = new InvoiceRequest();
            invoiceRequest.setDescription("");
            invoiceRequest.setPhone("");
            invoiceRequest.setProducts(ProductToCart.convertListCartItemDTOToThis(list));

            ApiService.apiService.createInvoice(invoiceRequest,user.getId()).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    Log.e("Create Invoice",response.toString());
                    Toast.makeText(CartActivity.this, "Create Invoice Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Log.e("Create Invoice","Failed");
                    Toast.makeText(CartActivity.this, "Create Invoice Failed", Toast.LENGTH_LONG).show();
                }
            });

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}