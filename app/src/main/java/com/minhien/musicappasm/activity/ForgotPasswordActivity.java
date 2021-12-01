package com.minhien.musicappasm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.minhien.musicappasm.R;
import com.minhien.musicappasm.model.ResponseModel;
import com.minhien.musicappasm.model.User;
import com.minhien.musicappasm.myritrofit.IRetrofitService;
import com.minhien.musicappasm.myritrofit.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText edtForgot;
    LinearLayout btnForgot;
    TextView tvLogin;
    private IRetrofitService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        service = new RetrofitBuilder().createService(IRetrofitService.class);
        tvLogin = findViewById(R.id.tvLogin);
        edtForgot = findViewById(R.id.edt_forgotPassword_email);
        btnForgot = findViewById(R.id.btnForgot);
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgot = edtForgot.getText().toString();
                service.forgot(new User(forgot,"")).enqueue(forgetpassCB);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
            }
        });
    }
    //retrofit login
    Callback<ResponseModel> forgetpassCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if(response.isSuccessful()){
                ResponseModel responseModel = response.body();
                if(responseModel.getStatus()){
                    startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                    Toast.makeText(ForgotPasswordActivity.this, "Vào check email đi", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ForgotPasswordActivity.this, "Bị gì rồi đó", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(ForgotPasswordActivity.this, "Chưa có email", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            Toast.makeText(ForgotPasswordActivity.this, "Thất bai", Toast.LENGTH_SHORT).show();
        }
    };
}