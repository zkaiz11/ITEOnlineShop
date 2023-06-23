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

import com.example.iteonlineshop.api.models.User;
import com.example.iteonlineshop.api.services.ProfileService;
import com.example.iteonlineshop.databinding.FragmentHomeBinding;
import com.example.iteonlineshop.databinding.FragmentProductBinding;
import com.example.iteonlineshop.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getProfileData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void getProfileData() {

        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/kimsongsao/ferupp/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProfileService profileService = httpClient.create(ProfileService.class);
        Call<User> task = profileService.getUserData();

        task.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    showUserData(response.body());
                } else {
                    Log.e("Profile Call", "Error" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Can't get product list Data", Toast.LENGTH_SHORT).show();
                Log.e("Profile Call", "Error" + t.getMessage());
            }
        });
    }
    private void showUserData(User userData) {
        binding.userFullNameProfile.setText(userData.getFirstName()+" "+userData.getLastName());
        binding.userEmailProfile.setText(userData.getEmail());
        Picasso.get().load(userData.getImgUrl()).into(binding.userImgProfile);
        binding.userEmail.setText(userData.getEmail());
        binding.userPhoneNumber.setText(userData.getPhoneNumber());
        binding.userGender.setText(userData.getGender());
        binding.userBirthday.setText(userData.getBirthday());
        binding.userAddress.setText(userData.getAddress());
    }
}
