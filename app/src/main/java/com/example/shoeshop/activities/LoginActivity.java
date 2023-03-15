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
import com.example.shoeshop.daos.UserDAO;
import com.example.shoeshop.model.Account;
import com.example.shoeshop.model.ResponseModel;
import com.example.shoeshop.model.UserDTO;
import com.example.shoeshop.service.ApiService;

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

//        UserDAO dao = new UserDAO(this);
//        UserDTO dto = dao.login(username, password);
//        if (dto == null) {
//            Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
//            return;
//        }
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
                        break;
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("Bye","Failure");
            }
        });

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

    private void saveToPreference(UserDTO dto) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("IDPref", dto.getUsername());
        editor.putString("EmailPref", dto.getEmail());
        editor.putString("Role", dto.getRole());
        editor.commit();
    }
}