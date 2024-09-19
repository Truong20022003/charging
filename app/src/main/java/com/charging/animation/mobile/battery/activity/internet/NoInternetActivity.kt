package com.charging.animation.mobile.battery.activity.internet

import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.splash.SplashActivity
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.databinding.ActivityNoInternetUpdateBinding
import com.charging.animation.mobile.battery.util.CheckInternet
import com.charging.animation.mobile.battery.util.Constants

class NoInternetActivity :  BaseActivity<ActivityNoInternetUpdateBinding, NoInternetViewModel>() {
    private var isIntent = ""
    override fun setBinding(layoutInflater: LayoutInflater): ActivityNoInternetUpdateBinding =
        ActivityNoInternetUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): NoInternetViewModel = viewModels<NoInternetViewModel>().value

    override fun init() {
        if (intent.getStringExtra(Constants.BUNDLE_SPLASH_NO_INTERNET) != null){
            isIntent = intent.getStringExtra(Constants.BUNDLE_SPLASH_NO_INTERNET)?:""
        }
        binding.imgNoIn.loadImage(this, R.drawable.no_internet_ic_wifi, binding.imgNoIn)
        binding.imgRetry.loadImage(this, R.drawable.language_bg_btn, binding.imgRetry)
        //EventTracking.logEvent(this, EventTracking.EVENT_NAME_NO_INTERNET_VIEW)

    }

    override fun listener() {
        binding.imgRetry.setOnClickListener {
            if (CheckInternet.haveNetworkConnection(this)) {
                finish()
                overridePendingTransition(0, 0)
            } else {
                gotoSetting()
            }
        }
    }

    override fun viewModel() {

    }

    override fun onResume() {
        super.onResume()
        if (CheckInternet.haveNetworkConnection(this)) {
            if (isIntent != ""){
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(0, 0)
            }else {
                finish()
                overridePendingTransition(0, 0)
            }
        }
    }

    private fun gotoSetting() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        startActivity(intent)
    }
}
