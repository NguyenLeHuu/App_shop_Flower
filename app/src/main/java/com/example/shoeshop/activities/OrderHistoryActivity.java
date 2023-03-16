package com.example.shoeshop.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoeshop.R;
import com.example.shoeshop.adapters.OrderAdapter;
import com.example.shoeshop.model.Invoice;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.service.ApiService;
import com.example.shoeshop.service.SharedPreferencesManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity {
    ListView listOrderHistoryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        listOrderHistoryView = findViewById(R.id.listOrderHistoryView);

        OrderAdapter adapter = new OrderAdapter();

        try {
            SharedPreferencesManager spm = new SharedPreferencesManager(OrderHistoryActivity.this);
            int userId = spm.getUser(OrderHistoryActivity.this).getId();

            ApiService.apiService.listInvoice(1, 10, userId).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    Log.e("Get History",response.toString());
                    ResponseModel responseModel = response.body();
                    ArrayList<Invoice> invoices = Invoice.convertObjectToList(responseModel.getData());

                    adapter.setOrderDTOArrayList(Invoice.convertInvoiceToOrderDTO(invoices));
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Log.e("Get History", "Failed");
                    Toast.makeText(OrderHistoryActivity.this, "Cannot get History.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}