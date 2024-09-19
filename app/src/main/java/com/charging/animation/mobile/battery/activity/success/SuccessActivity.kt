package com.charging.animation.mobile.battery.activity.success

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivitySuccessBinding

@Suppress("DEPRECATION")
class SuccessActivity : BaseActivity<ActivitySuccessBinding, SuccessViewModel>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySuccessBinding = ActivitySuccessBinding.inflate(layoutInflater)

    override fun setViewModel(): SuccessViewModel = viewModels<SuccessViewModel>().value
    override fun init() {
        CommonAds.logEvent(this@SuccessActivity, "item_sucess_view")
        CommonAds.logEvent(this@SuccessActivity, "animation_success")
        CommonAds.logEvent(this@SuccessActivity, "animation_success_view")
        binding.gif.loadImage(this, R.drawable.success,  binding.gif,true)
    }

    override fun listener() {
        binding.imgRetry.tap {
            CommonAds.logEvent(this@SuccessActivity, "item_sucess_explore_click")
            showActivityFinishAffinity(MainActivity::class.java)
        }

        binding.imgRetry.loadImage(this, R.drawable.language_bg_btn, binding.imgRetry)
        binding.imageView2.loadImage(this,R.drawable.success_ic_done, binding.imageView2)
    }

    override fun viewModel() {

    }

}