package com.minhien.musicappasm.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.minhien.musicappasm.activity.ForgotPasswordActivity;
import com.minhien.musicappasm.activity.HomeActivity;
import com.minhien.musicappasm.activity.MainActivity;
import com.minhien.musicappasm.myritrofit.IRetrofitService;
import com.minhien.musicappasm.myritrofit.RetrofitBuilder;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.model.ResponseModel;
import com.minhien.musicappasm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {
    Button btnLogin;
    EditText edtEmail, edtPassword;
    TextView tvResetPassword;
    IRetrofitService service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        edtEmail = view.findViewById(R.id.email);
        edtPassword = view.findViewById(R.id.pass);
        tvResetPassword = view.findViewById(R.id.tvResetPassword);
        service = new RetrofitBuilder().createService(IRetrofitService.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _us = edtEmail.getText().toString();
                String _pw = edtPassword.getText().toString();
                User user = new User(_us,_pw);
                service.login(user).enqueue(loginCB);
                getActivity().finish();
            }
        });
        tvResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ForgotPasswordActivity.class));
            }
        });
        return view;
    }

    Callback<ResponseModel> loginCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if(response.isSuccessful()){
                ResponseModel responseModel = response.body();
                if(responseModel.getStatus()){
                    startActivity(new Intent(getActivity(),HomeActivity.class));
                }
            }
            else{
                Log.e("onResponse >>>>>>>>>>", response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            Log.e("onFailure >>>>>>>>>>", t.getLocalizedMessage());
        }
    };
}