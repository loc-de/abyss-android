package com.abyss.abyss.data.network;

import com.abyss.abyss.data.model.RegisterRequest;
import com.abyss.abyss.data.model.LoginRequest;
import com.abyss.abyss.data.model.LoginResponse;
import com.abyss.abyss.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ApiService {

    @POST("/api/auth/register")
    Call<okhttp3.ResponseBody> register(@Body RegisterRequest request);

    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("/api/users/me")
    Call<User> getProfile();

}
