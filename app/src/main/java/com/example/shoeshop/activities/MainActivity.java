package com.example.shoeshop.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoeshop.R;
import com.example.shoeshop.adapters.ShoeAdapter;
import com.example.shoeshop.model.Flower;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.model.ShoesDTO;
import com.example.shoeshop.model.User;
import com.example.shoeshop.service.ApiService;
import com.example.shoeshop.service.SharedPreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listShoesView;
    private ArrayList<ShoesDTO> shoesDTOList;
    private ShoeAdapter adapter;
    private AlertDialog.Builder alertDialog;

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
                break;
            case R.id.menuExit:
                logout();
                break;
            case R.id.menuHistory:
                intent = new Intent(this, OrderHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.menuMap:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("IDPref");
//        editor.remove("EmailPref");
//        editor.remove("Role");
        SharedPreferencesManager spm = new SharedPreferencesManager(MainActivity.this);
        spm.saveUser(new User(0,""));
        spm.saveAccessToken("");
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

//        CartDAO dao = new CartDAO(this);
//        try {
//            boolean success = dao.clearCart();
//            if (success) {
//                Intent intent = new Intent(this, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//            } else {
//                Toast.makeText(this, "Cannot logout.", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Flower List");

        listShoesView = findViewById(R.id.listShoesView);
        adapter = new ShoeAdapter();
        ArrayList<Flower> lists = new ArrayList<>();

        ApiService.apiService.listProduct(20).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                for (Flower data : Flower.convertObjectToList(responseModel.getData())) {
                    lists.add(data);
                }
                shoesDTOList = Flower.convertListFlowerToShoesItem(lists);
                adapter.setFruitDTOList(shoesDTOList);
                listShoesView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Get Failed",Toast.LENGTH_SHORT).show();
            }
        });


//        ShoesDAO dao = new ShoesDAO(MainActivity.this);
//        try {
//            shoesDTOList = dao.getFruits();
//            adapter.setFruitDTOList(shoesDTOList);
//            listShoesView.setAdapter(adapter);
//
//            CartDAO cartDAO = new CartDAO(this);
//            boolean hasCart = cartDAO.getCartItems().size() > 0;
//
//            if (hasCart) {
//                alertDialog = new AlertDialog.Builder(this);
//                alertDialog.setTitle("You have items inside your Cart. Do you want to check?");
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(MainActivity.this, CartActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                alertDialog.show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



    }


}