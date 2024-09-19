package com.charging.animation.mobile.battery.activity.info.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.charging.animation.mobile.battery.activity.info.fragment.battery.BatteryFragment
import com.charging.animation.mobile.battery.activity.info.fragment.mobile.MobileFragment

class TopAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return BatteryFragment()
            }

            1 -> {
                return MobileFragment()
            }
        }
        return BatteryFragment()
    }

    override fun getCount(): Int {
        return 2
    }
}