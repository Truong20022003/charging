package com.charging.animation.mobile.battery.fragment.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.base.BaseFragment;
import com.charging.animation.mobile.battery.databinding.FragmentFirstBinding;

public class FirstFragment extends BaseFragment<FragmentFirstBinding> {
    @NonNull
    @Override
    public FragmentFirstBinding getViewBinding() {
        return FragmentFirstBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.btnCustom.setOnClickListener(v -> {
            try {
                CommonAds.logEvent(requireActivity(), "home_custom_click");
                ((MainActivity) requireActivity()).select();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.btnCustom.setSelected(true);
    }

}

