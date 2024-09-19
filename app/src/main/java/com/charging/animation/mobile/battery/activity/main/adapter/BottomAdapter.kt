package com.charging.animation.mobile.battery.activity.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.charging.animation.mobile.battery.fragment.alarm.AlarmFragment
import com.charging.animation.mobile.battery.fragment.gallery.GalleryFragment
import com.charging.animation.mobile.battery.fragment.home.HomeFragment
import com.charging.animation.mobile.battery.fragment.setting.SettingAndAlarmFragment
import com.charging.animation.mobile.battery.fragment.setting.SettingFragment

class BottomAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }

            1 -> {
                return GalleryFragment()
            }

            2 -> {
                return SettingAndAlarmFragment()
            }
        }
        return HomeFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}