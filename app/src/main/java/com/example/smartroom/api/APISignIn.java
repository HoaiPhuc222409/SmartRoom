package com.example.smartroom.api;

import com.example.smartroom.service.LoginRequest;
import com.example.smartroom.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APISignIn {
    @Headers({
            "Content-Type:application/json",
    })
    @POST("auth/signin")
    Call<Token> getToken(@Body LoginRequest account);
}
