package com.example.dan.testingapp.api;

import com.example.dan.testingapp.model.UserData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by DAN on 7/19/2018.
 */

public interface ApiInterface {

    @GET("/api/v1/users/")
    Call<UserData> getData();

    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    Call<ResponseBody> loginAuth(
            @Field("email") String email,
            @Field("password") String password
    );
}
