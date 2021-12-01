package com.minhien.musicappasm.myritrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
//    private String BASE_URL = "http://10.0.2.2:8081/";
    private String BASE_URL = "https://nightowlzshop12345.000webhostapp.com/testDNDK/";
//    private static final Retrofit retrofit = buildRetrofit();

    private Retrofit buildRetrofit(){
        Gson gson = new GsonBuilder().setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public <T> T createService(Class<T> service){
        return buildRetrofit().create(service);
    }
    public <T> T createService2Pik(Class<T> service, String url){
        BASE_URL = url;
        return buildRetrofit().create(service);
    }
}
