package com.abyss.abyss.data.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.143:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getInstanceWithAuth(String token) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.143:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(chain -> {
                    return chain.proceed(chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build());
                }).build())
                .build();
    }

}
