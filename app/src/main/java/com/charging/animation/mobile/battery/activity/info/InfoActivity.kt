package com.charging.animation.mobile.battery.activity.info

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.info.adapter.TopAdapter
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivityInfoBinding

@Suppress("DEPRECATION")
class InfoActivity : BaseActivity<ActivityInfoBinding, InfoViewModel>() {
    override fun setBinding(layoutInflater: LayoutInflater): ActivityInfoBinding = ActivityInfoBinding.inflate(layoutInflater)

    override fun setViewModel(): InfoViewModel =  viewModels<InfoViewModel>().value

    override fun init() {
        CommonAds.logEvent(this@InfoActivity, "info_battery_view")
        val homeAdapter = TopAdapter(supportFragmentManager, 1)
        binding.viewpager.adapter = homeAdapter
        binding.viewpager.offscreenPageLimit = 2
        binding.btnCustom.isSelected = true
        binding.btnDown.isSelected = true
    }

    override fun listener() {
        binding.btnDown.tap {
            CommonAds.logEvent(this@InfoActivity, "info_battery_view")
            binding.btnDown.setBackgroundResource(R.drawable.gallery_btn_select)
            binding.btnCustom.setBackgroundResource(android.R.color.transparent)
            binding.viewpager.setCurrentItem(0, true)
        }
        binding.btnCustom.tap {
            CommonAds.logEvent(this@InfoActivity, "info_device_view")
            binding.btnCustom.setBackgroundResource(R.drawable.gallery_btn_select)
            binding.btnDown.setBackgroundResource(android.R.color.transparent)
            binding.viewpager.setCurrentItem(1, true)
        }
        binding.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.btnDown.setBackgroundResource(R.drawable.gallery_btn_select)
                    binding.btnCustom.setBackgroundResource(android.R.color.transparent)
                } else {
                    binding.btnCustom.setBackgroundResource(R.drawable.gallery_btn_select)
                    binding.btnDown.setBackgroundResource(android.R.color.transparent)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.imgBack.tap { onBackPressed() }
    }

    override fun viewModel() {
        
    }
    
}