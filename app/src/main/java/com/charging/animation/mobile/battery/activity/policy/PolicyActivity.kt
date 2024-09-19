package com.charging.animation.mobile.battery.activity.policy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivityPolicyUpdateBinding

class PolicyActivity : BaseActivity<ActivityPolicyUpdateBinding, PolicyViewModel>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityPolicyUpdateBinding = ActivityPolicyUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): PolicyViewModel = viewModels<PolicyViewModel>().value

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        try{
            val webSettings = binding.webView.settings
            webSettings.javaScriptEnabled = true
            binding.webView.loadUrl("https://www.google.com")
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
    override fun listener() {}
    override fun viewModel() {
        binding.imgBack.tap { onBackPressed() }
    }
}