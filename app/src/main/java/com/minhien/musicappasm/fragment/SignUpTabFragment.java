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
import android.widget.Toast;

import com.minhien.musicappasm.activity.HomeActivity;
import com.minhien.musicappasm.myritrofit.IRetrofitService;
import com.minhien.musicappasm.myritrofit.RetrofitBuilder;
import com.minhien.musicappasm.R;
import com.minhien.musicappasm.model.ResponseModel;
import com.minhien.musicappasm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpTabFragment extends Fragment {
    Button btn;
    EditText edtEmail, edtPassword, edtConfirmPassword;
    IRetrofitService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_tab_fragment, container, false);
        btn = view.findViewById(R.id.btnSignUp);
        edtEmail = view.findViewById(R.id.edtemail);
        edtPassword = view.findViewById(R.id.edtpass);
        edtConfirmPassword = view.findViewById(R.id.confirm_password);
        service = new RetrofitBuilder().createService(IRetrofitService.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _us = edtEmail.getText().toString();
                String _pw = edtPassword.getText().toString();
                String _cfpw = edtConfirmPassword.getText().toString();
                User user = new User(_us, _pw);
                service.register(user).enqueue(registerCB);
            }
        });
        return view;
    }

    Callback<ResponseModel> registerCB = new Callback<ResponseModel>() {
        @Override
        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            if (response.isSuccessful()) {
                ResponseModel responseModel = response.body();
                if (responseModel.getStatus()) {
                    Intent i = new Intent(getActivity(), HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e(">>>>>>>>>>", response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponseModel> call, Throwable t) {
            Log.e(">>>>>>>>>>", t.getLocalizedMessage());
        }
    };
}