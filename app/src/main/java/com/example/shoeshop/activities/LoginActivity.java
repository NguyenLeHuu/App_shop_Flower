package com.example.shoeshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoeshop.R;
import com.example.shoeshop.constants.Constants;
import com.example.shoeshop.model.Account;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.model.ShoesDTO;
import com.example.shoeshop.model.User;
import com.example.shoeshop.model.UserDTO;
import com.example.shoeshop.service.ApiService;
import com.example.shoeshop.service.SharedPreferencesManager;
import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }


    public void clickToLogin(View view) throws Exception {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        SharedPreferencesManager spm = new SharedPreferencesManager(LoginActivity.this);

        ApiService.apiService.login(new Account(username,password)).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("Hello",response.toString());
                switch (response.code()){
                    case 400:
                        Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                        break;
                    case 200:
                        Toast.makeText(LoginActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                        ResponseModel responseModel = response.body();
                        String accessToken = (String)responseModel.getData();
                        spm.saveAccessToken(accessToken);
                        break;
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("Bye","Failure");
            }
        });

        String accessTk = spm.getAccessToken();
        if (accessTk !=""){
            ApiService.apiService.getUserByAccessToken("Bearer " + accessTk).enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if (response.code()== 200)
                    {
                    ResponseModel responseModel = response.body();

                    User user = User.convertObjectToUser(responseModel.getData());
                    spm.saveUser(user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    } else {
                        Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            });
        }

//        Intent intent = new Intent(this, MainActivity.class);
////        intent.putExtra("DTO", dto);
////        saveToPreference(dto);
//        startActivity(intent);
//        finish();

    }

    public void clickToSwitchToSignup(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

//    private void saveToPreference(UserDTO dto) {
//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("IDPref", dto.getUsername());
//        editor.putString("EmailPref", dto.getEmail());
//        editor.putString("Role", dto.getRole());
//        editor.commit();
//    }
}