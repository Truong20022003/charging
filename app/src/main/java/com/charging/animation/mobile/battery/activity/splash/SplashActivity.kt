package com.charging.animation.mobile.battery.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.Common
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.internet.NoInternetActivity
import com.charging.animation.mobile.battery.activity.language.LanguageStartActivity
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.activity.permission.PermissionActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.databinding.ActivitySpalshBinding
import com.charging.animation.mobile.battery.util.CheckInternet
import com.charging.animation.mobile.battery.util.Constants


@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashActivity : BaseActivity<ActivitySpalshBinding, SplashViewModel>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySpalshBinding =
        ActivitySpalshBinding.inflate(layoutInflater)

    override fun setViewModel(): SplashViewModel = viewModels<SplashViewModel>().value

    override fun init() {

        CommonAds.logEvent(this, "splash_open")

        if (!isTaskRoot && intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intent.action != null && intent.action.equals(Intent.ACTION_MAIN)) {
            finish()
            return
        }

        try {
            Glide.with(this).load(R.drawable.splash_img_logo).into(binding.imgLogo)
            Glide.with(this).load(R.drawable.splash_img_bg).into(binding.imgBackground)
        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    override fun listener() {

    }

    override fun viewModel() {
        viewModel.progress.observe(this) { progress ->
            binding.pb.progress = progress
            binding.tvPer.text = "$progress%"
        }
        viewModel.startCountdown()
        viewModel.countdownFinished.observe(this) {
            start()
        }
    }

    fun start(){
        if (!CheckInternet.haveNetworkConnection(this)) {
            val bundle = Bundle()
            bundle.putString(Constants.BUNDLE_SPLASH_NO_INTERNET, Constants.BUNDLE_SPLASH)
            overridePendingTransition(0, 0)
            showActivityFinish( NoInternetActivity::class.java, bundle)
        } else {
            showActivityFinish(LanguageStartActivity::class.java)
            finish()
        }
    }
}
