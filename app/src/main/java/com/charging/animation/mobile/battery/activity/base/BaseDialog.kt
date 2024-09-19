package com.charging.animation.mobile.battery.activity.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.charging.animation.mobile.battery.R

abstract class BaseDialog <T : ViewBinding>(context: Context) :  Dialog(context, R.style.full_screen_dialog) {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout(layoutInflater))
        initView()
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): T

    private fun getInflatedLayout(inflater: LayoutInflater): View {
        binding = setBinding(inflater)
        return binding.root
    }

    abstract fun initView()
}