package com.example.myaddressbook.api;


import com.example.myaddressbook.model.AllRespModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("?nim=2301945332&nama=dimasramandhika")
    Call<AllRespModel> RetrieveData();

}
