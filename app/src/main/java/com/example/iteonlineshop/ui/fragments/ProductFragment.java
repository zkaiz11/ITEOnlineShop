package com.example.iteonlineshop.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.api.models.Product;
import com.example.iteonlineshop.api.services.ProductService;
import com.example.iteonlineshop.databinding.FragmentProductBinding;
import com.example.iteonlineshop.ui.adapters.ProductAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getProductLists();
        super.onViewCreated(view, savedInstanceState);
    }

    private void getProductLists() {

        // Create retrofit client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/kimsongsao/ferupp/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create Service object
        ProductService productService = httpClient.create(ProductService.class);

        Call<List<Product>> task = productService.loadProductLists();

        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    showProductList(response.body());
                } else {
                    Toast.makeText(getContext(), "Can't get product list Data", Toast.LENGTH_SHORT).show();
                    Log.e("Product Call", "Error" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Can't get product list Data", Toast.LENGTH_SHORT).show();
                Log.e("Product Call", "Error" + t.getMessage());
            }
        });
    }

    private void showProductList (List<Product> productList) {

        //Create layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.productItemViewGroup.setLayoutManager(linearLayoutManager);

        // Create Adapter
        ProductAdapter adapter = new ProductAdapter();

        adapter.submitList(productList);
        binding.productItemViewGroup.setAdapter(adapter);
    }
}
