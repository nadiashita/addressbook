package com.example.addressbook.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/stage2/people/";
    private static Retrofit retro;

    public static Retrofit getService2() {
        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }

    public static APIRequestData getService(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL + id)
                .client(createOkHttpClientJSON())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIRequestData service = retrofit.create(APIRequestData.class);
        return service;
    }

    private static OkHttpClient createOkHttpClientJSON() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS);

        return httpClient.build();
    }

}
