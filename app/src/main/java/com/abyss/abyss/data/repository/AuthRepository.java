package com.abyss.abyss.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.abyss.abyss.data.model.LoginRequest;
import com.abyss.abyss.data.model.LoginResponse;
import com.abyss.abyss.data.network.ApiService;
import com.abyss.abyss.data.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final ApiService api;

    public AuthRepository() {
        api = RetrofitClient.getInstance().create(ApiService.class);
    }

    public void login(String username, String password, MutableLiveData<String> tokenLiveData, MutableLiveData<String> errorLiveData) {
        api.login(new LoginRequest(username, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tokenLiveData.postValue(response.body().getToken());
                } else {
                    errorLiveData.postValue("Login failed");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                errorLiveData.postValue("Error: " + t.getMessage());
            }
        });
    }

}
