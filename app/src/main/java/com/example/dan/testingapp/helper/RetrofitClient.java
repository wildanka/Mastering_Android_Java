package com.example.dan.testingapp.helper;

import com.example.dan.testingapp.api.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DAN on 7/20/2018.
 */

//this is a singleton class
public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.88.12:8000/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit = null;

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ApiInterface getApiInterface(){
        return retrofit.create(ApiInterface.class);
    }

}
