package com.charging.animation.mobile.battery.activity.animation

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivityAnimationupdateBinding
import com.charging.animation.mobile.battery.util.Data
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class AnimationActivity : BaseActivity<ActivityAnimationupdateBinding, AnimationViewModel>() {
    private var doubleBackToExitPressedOnce = false

    override fun setBinding(layoutInflater: LayoutInflater): ActivityAnimationupdateBinding =
        ActivityAnimationupdateBinding.inflate(layoutInflater)

    override fun setViewModel(): AnimationViewModel = viewModels<AnimationViewModel>().value

    override fun init() {
        val link = Data.getPathAnim(this)
        if (link.contains(".gif") || link.contains(".GIF")) {
            Glide.with(this).asGif().load(link).into(binding.gitAnim)
        } else {
            Glide.with(this).load(link).into(binding.gitAnim)
        }
        blur()
        setUpView()
        receiver()
        isChanging()
    }

    override fun listener() {
        when (Data.getSettingOff(this)) {
            0 -> {
                binding.root.tap { finish() }
            }

            1 -> {
                binding.root.tap {
                    if (doubleBackToExitPressedOnce) {
                        super.onBackPressed()
                        return@tap
                    }
                    doubleBackToExitPressedOnce = true
                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
            }
        }
    }

    override fun viewModel() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen()
        ACTIVE = true

    }

    private fun screen() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        val win = window
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }
    }

    fun receiver() {
        val filter = IntentFilter()
        filter.addAction(ACTION_STOP_ANIMATION)
        filter.addAction(Intent.ACTION_TIME_TICK)
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        filter.addAction(Intent.ACTION_TIME_CHANGED)
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(receiver, filter)
        }
    }


    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == ACTION_STOP_ANIMATION) {
                try {
                    unregisterReceiver(this)
                    finish()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
            if (action == Intent.ACTION_TIME_CHANGED || action == Intent.ACTION_TIMEZONE_CHANGED || action == Intent.ACTION_TIME_TICK) {
                setUpView()
            }
            when (intent.action) {
                Intent.ACTION_BATTERY_CHANGED -> {
                    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                    val batteryPct = (level * 100 / scale.toFloat()).toInt()
                    binding.tvProgress.text = "$batteryPct%"
                    binding.circularProgressBar.progress = batteryPct.toFloat()
                }

                Intent.ACTION_POWER_CONNECTED -> {
                    binding.tvConnect.visibility = View.VISIBLE
                }

                else -> {
                    binding.tvConnect.visibility = View.GONE
                }
            }
        }
    }


    private fun close(time: Int) {
        try {
            Handler(Looper.getMainLooper()).postDelayed({ finish() }, time.toLong())
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        when (Data.getSettingTime(this)) {
            0 -> {
                close(5000)
            }

            1 -> {
                close(10000)
            }

            2 -> {
                close(15000)
            }

            3 -> {
                close(20000)
            }

            4 -> {
                close(25000)
            }

            5 -> {
                close(30000)
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun isChanging() {
        binding.tvConnect.text = getString(R.string.charging) + " ..."
        val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        var status = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                val chargingStatus =
                    batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS)
                status = (chargingStatus == BatteryManager.BATTERY_STATUS_CHARGING
                        || chargingStatus == BatteryManager.BATTERY_STATUS_FULL)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }

        if (status) {
            binding.tvConnect.visibility = View.VISIBLE
        } else {
            binding.tvConnect.visibility = View.GONE
        }
    }


    private fun setUpView() {
        binding.tvDay.text = getCurrentDateInCustomFormat()
        binding.tvTime.text = getCurrentTimeInCustomFormat()
        binding.imgBtnDown.loadImage(this, R.drawable.dowm_bg_btn, binding.imgBtnDown)
    }

    private fun getCurrentTimeInCustomFormat(): String {
        val currentDate = Date()
        val outputFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        return outputFormat.format(currentDate)
    }

    private fun getCurrentDateInCustomFormat(): String {
        val currentDate = Date()
        val outputFormat = SimpleDateFormat("EEEE, dd MMM", Locale.ENGLISH)
        return outputFormat.format(currentDate)
    }


    private fun getBlurAlgorithm(): BlurAlgorithm {
        val algorithm: BlurAlgorithm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            RenderEffectBlur()
        } else {
            RenderScriptBlur(this)
        }
        return algorithm
    }

    private fun blur() {
        try {
            val windowBackground: Drawable = window.decorView.background

            binding.blurViewCharging.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        try {
            ACTIVE = false
            receiver.let {
                LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
                unregisterReceiver(receiver)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        @JvmField
        var ACTION_STOP_ANIMATION = "ACTION_STOP_ANIMATION"

        @JvmField
        var ACTIVE = false
    }
}