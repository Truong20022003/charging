package com.charging.animation.mobile.battery.fragment.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.charging.animation.mobile.battery.fragment.home.fragment.FirstFragment;
import com.charging.animation.mobile.battery.fragment.home.fragment.SecondFragment;

public class HeaderAdapter extends FragmentPagerAdapter {


    public HeaderAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0 :{
               return new SecondFragment();
           }

           case 1 :{
               return new FirstFragment();
           }
       }
        return new FirstFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
