package com.example.addressbook.Api;

import com.example.addressbook.Model.AllModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("?nim=2301931094&nama=MuhammadDangga")
    Call<AllModel> RetrieveData();

}
