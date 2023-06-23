package com.example.iteonlineshop.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.iteonlineshop.R;
import com.example.iteonlineshop.databinding.ActivityLandingBinding;
import com.example.iteonlineshop.ui.fragments.HomeFragment;
import com.example.iteonlineshop.ui.fragments.MoreFragment;
import com.example.iteonlineshop.ui.fragments.ProductFragment;
import com.example.iteonlineshop.ui.fragments.ProfileFragment;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavBar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.mnuHome) {
                HomeFragment homeFragment = new HomeFragment();
                showFragment(homeFragment);
            } else if (item.getItemId() == R.id.mnuProduct) {
                showFragment(new ProductFragment());
            } else if (item.getItemId() == R.id.mnuProfile) {
                showFragment(new ProfileFragment());
            } else {
                showFragment(new MoreFragment());
            }
            return true;
        });

        showFragment(new HomeFragment());

    }

    private void showFragment(Fragment fragment) {

        // Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace Home Fragment
        fragmentTransaction.replace(R.id.lytFragment, fragment);

        // Commit
        fragmentTransaction.commit();

    }
}
