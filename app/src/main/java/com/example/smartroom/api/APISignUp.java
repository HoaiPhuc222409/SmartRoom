package com.example.smartroom.api;

import com.example.smartroom.model.ResponseSignUpBody;
import com.example.smartroom.service.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APISignUp {
    @Headers({
            "Content-Type:application/json",
    })
    @POST("auth/signup")
    Call<ResponseSignUpBody> signUp(@Body SignupRequest signupRequest);
}
