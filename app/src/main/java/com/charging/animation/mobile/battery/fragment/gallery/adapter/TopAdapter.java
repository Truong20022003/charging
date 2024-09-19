package com.charging.animation.mobile.battery.fragment.gallery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.charging.animation.mobile.battery.fragment.gallery.fragment.CustomFragment;
import com.charging.animation.mobile.battery.fragment.gallery.fragment.DownFragment;

public class TopAdapter extends FragmentPagerAdapter {


    public TopAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position) {
           case 0: {
               return new DownFragment();
           }

           case 1: {
               return new CustomFragment();
           }
       }
        return new DownFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
