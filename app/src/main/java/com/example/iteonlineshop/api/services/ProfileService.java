package com.example.iteonlineshop.api.services;

import com.example.iteonlineshop.api.models.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileService {

    @GET("profile.json")
    Call<User> getUserData();
}
