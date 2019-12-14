package com.example.smartroom.service;

import com.example.smartroom.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ProductService {

    @GET("/history")
    Call<ArrayList<Product>> getProducts(@Header("Authorization") String token, @Query("valueName")String valueName, @Query("number") String number);
}
