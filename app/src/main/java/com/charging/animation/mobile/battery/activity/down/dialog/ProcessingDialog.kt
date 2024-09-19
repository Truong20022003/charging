package com.charging.animation.mobile.battery.activity.down.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseDialog
import com.charging.animation.mobile.battery.databinding.DialogProcessingBinding
import java.util.Objects

@Suppress("DEPRECATION")
class ProcessingDialog(activity: Activity) : BaseDialog<DialogProcessingBinding>(activity) {

    private fun fullScreen() {
        try {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
            val lp = window?.attributes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                lp?.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
            window?.attributes = lp
        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    override fun setBinding(layoutInflater: LayoutInflater): DialogProcessingBinding = DialogProcessingBinding.inflate(layoutInflater)

    override fun initView() {
        fullScreen()
    }

}