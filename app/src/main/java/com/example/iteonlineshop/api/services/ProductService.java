package com.example.iteonlineshop.api.services;

import com.example.iteonlineshop.api.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("products.json")
    Call<List<Product>> loadProductLists();
}
