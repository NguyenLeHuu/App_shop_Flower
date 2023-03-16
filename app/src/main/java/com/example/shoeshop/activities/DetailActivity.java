package com.example.shoeshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoeshop.model.CartItemDTO;
import com.example.shoeshop.model.ProductToCart;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.model.ShoesDTO;
import com.example.shoeshop.R;
import com.example.shoeshop.model.User;
import com.example.shoeshop.service.ApiService;
import com.example.shoeshop.service.SharedPreferencesManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView txtName , txtPrice, txtDesc;
    private ImageView imgFruitView;
    private Button btnAddToCart;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCart:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ShoesDTO dto  = (ShoesDTO) getIntent().getSerializableExtra("DTO");
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDesc = findViewById(R.id.txtDesc);
        imgFruitView = findViewById(R.id.imgFruitView);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        txtName.setText(dto.getName());
        txtPrice.setText(dto.getPrice() + "");
        txtDesc.setText(dto.getDescription());
        Picasso.get().load(dto.getImage()).into(imgFruitView);


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferencesManager spm = new SharedPreferencesManager(v.getContext());
                User user = spm.getUser(v.getContext());
                ApiService.apiService.addProductToCart(new ProductToCart(dto.getId(),1),user.getId()).enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.e("Hello",response.toString());
                        Toast.makeText(DetailActivity.this, "Added to Cart Successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.e("Hello","Failed");
                        Toast.makeText(DetailActivity.this, "Added to Cart Failed", Toast.LENGTH_SHORT).show();
                    }
                });

//
//                CartDAO dao = new CartDAO(v.getContext());
//                try {
//                    CartItemDTO cartItem = dao.getCartItem(dto.getId());
//                    if (cartItem == null) {
//                        cartItem = new CartItemDTO(dto.getId(), dto.getName(), 1, dto.getPrice(), dto.getImage());
//                        dao.addToCart(cartItem);
//                    } else {
//                        int quantity = cartItem.getQuantity() + 1;
//                        dao.updateCart(dto.getId(), quantity);
//                    }
//                    Toast.makeText(v.getContext(), "Added to Cart", Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });


    }


    public void clickAddProductToCart(View v){
        SharedPreferencesManager spm = new SharedPreferencesManager(v.getContext());
        User user = spm.getUser(v.getContext());
        ShoesDTO dto  = (ShoesDTO) getIntent().getSerializableExtra("DTO");
        ApiService.apiService.addProductToCart(new ProductToCart(dto.getId(),1),user.getId()).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(v.getContext(), "Added to Cart Successful", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(v.getContext(), "Added to Cart Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}