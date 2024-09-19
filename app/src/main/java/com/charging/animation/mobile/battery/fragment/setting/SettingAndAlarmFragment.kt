package com.charging.animation.mobile.battery.fragment.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.charging.animation.mobile.battery.R

class SettingAndAlarmFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting_and_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create an instance of SettingFragment
        val settingFragment = SettingFragment()

        // Replace the fragment_view with SettingFragment
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_view, settingFragment)
            .commit()
    }

}