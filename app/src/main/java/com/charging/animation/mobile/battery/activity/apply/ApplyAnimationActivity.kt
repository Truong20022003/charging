package com.charging.animation.mobile.battery.activity.apply

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.WallpaperManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.success.SuccessActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivityApplyAnimationBinding
import com.charging.animation.mobile.battery.databinding.LayoutWalpaperSelectOptionBinding
import com.charging.animation.mobile.battery.dialog.DialogSetting
import com.charging.animation.mobile.battery.listener.Listener
import com.charging.animation.mobile.battery.service.LiveWallpaperService
import com.charging.animation.mobile.battery.util.Data
import com.charging.animation.mobile.battery.util.SharePrefUtils
import com.charging.animation.mobile.battery.util.SystemUtil
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DEPRECATION")
class ApplyAnimationActivity :
    BaseActivity<ActivityApplyAnimationBinding, ApplyAnimationViewModel>() {
    var link: String? = ""
    var type = 0
    private var isClicked = false
    private var isPermission = false

    override fun setBinding(layoutInflater: LayoutInflater): ActivityApplyAnimationBinding =
        ActivityApplyAnimationBinding.inflate(layoutInflater)

    override fun setViewModel(): ApplyAnimationViewModel =
        viewModels<ApplyAnimationViewModel>().value

    override fun init() {
        CommonAds.logEvent(this@ApplyAnimationActivity, "item_apply_view")
        binding.imgBtnDown.loadImage(this, R.drawable.dowm_bg_wallpaper, binding.imgBtnDown)
        binding.imgBtnApply.loadImage(this, R.drawable.dowm_bg_applly, binding.imgBtnApply)
        binding.imgCreator.loadImage(this, R.drawable.dowm_bg_creator, binding.imgCreator)

        link = intent.getStringExtra("link")
        type = intent.getIntExtra("type", 0)

        if (type == 0) {
            if (link?.contains(".gif") == true || link?.contains(".GIF") == true) {
                Glide.with(this)
                    .asGif()
                    .load(Uri.parse(link))
                    .into(binding.imgGif)
            } else {
                getBitmap(link, object : OnClickedListener {
                    override fun onClicked(bitmap: Bitmap?) {
                        binding.imgGif.setImageBitmap(bitmap)
                    }
                })
            }
        } else {
            if (link?.contains(".gif") == true || link?.contains(".GIF") == true) {
                Glide.with(this)
                    .asGif()
                    .load(link)
                    .into(binding.imgGif)
            } else {
                getBitmap(link, object : OnClickedListener {
                    override fun onClicked(bitmap: Bitmap?) {
                        binding.imgGif.setImageBitmap(bitmap)
                    }
                })
            }
        }
        blur()
        setUpView()
        receiver()
        isChanging()
    }

    @SuppressLint("SetTextI18n")
    private fun isChanging(){
        receiver()
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

    fun receiver(){
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        }else{
           registerReceiver(receiver, filter)
        }

    }

    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context, intent: Intent) {
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

            binding.blurViewMain.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)

            binding.blurViewWallpaper.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)

            binding.blurViewCreator.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)

            binding.blurViewCharging.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


    override fun listener() {

        val dialogSetting = DialogSetting(this@ApplyAnimationActivity, object : Listener() {
            override fun onApply() {
                super.onApply()
                Data.setPathAnim(baseContext, link)
                done()
            }
        }, this@ApplyAnimationActivity)


        dialogSetting.setOnDismissListener {
            if (SystemUtil.isNetworkConnected(baseContext)) {
                binding.frAds.visibility = View.VISIBLE
            } else {
                binding.frAds.visibility = View.GONE
            }
            if (!getSharedPreferences("REMOTE", MODE_PRIVATE).getBoolean(
                    "native_apply",
                    CommonAds.native_apply
                )
            ) {
                binding.frAds.visibility = View.GONE
            }
        }

        binding.btnApply.tap {
            if (checkOverlayPermission(this)) {
                CommonAds.logEvent(this@ApplyAnimationActivity, "item_apply_click")
                binding.frAds.visibility = View.GONE
                dialogSetting.show()
            } else {
                dialogForPermission()
            }
        }

        binding.btnSetWallpaper.tap {
            CommonAds.logEvent(this@ApplyAnimationActivity, "item_set_wallpaper_click")
            Data.TYPE = type
            Data.PATH = link
            if (link?.contains(".gif") == true || link?.contains(".GIF") == true) {
                val wallpaperManager: WallpaperManager =
                    WallpaperManager.getInstance(this@ApplyAnimationActivity)
                try {
                    wallpaperManager.clear()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                try {
                    val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
                    intent.putExtra(
                        WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, ComponentName(
                            applicationContext, LiveWallpaperService::class.java
                        )
                    )
                    startActivity(intent)
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            } else {
                getBitmap(link, object : OnClickedListener {
                    override fun onClicked(bitmap: Bitmap?) {
                        if (!isClicked) {
                            isClicked = true
                            showDialogSetWallpaper(bitmap)
                        }
                    }
                })
            }
        }

        binding.imgBack.tap { onBackPressed() }
    }

    override fun viewModel() {

    }



    private fun checkOverlayPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(context)
        } else {
            true
        }
    }

    private fun dialogForPermission() {
        val dialog2 = Dialog(this@ApplyAnimationActivity)
        dialog2.setContentView(R.layout.dialog_overlay_permisson)
        dialog2.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog2.show()
        val closeButton = dialog2.findViewById<Button>(R.id.cbutton)
        closeButton.setOnClickListener {
            dialog2.cancel()
            try {
                if ("xiaomi" == Build.MANUFACTURER.toLowerCase(Locale.ROOT)) {
                    val intent = Intent("miui.intent.action.APP_PERM_EDITOR").apply {
                        setClassName(
                            "com.miui.securitycenter",
                            "com.miui.permcenter.permissions.PermissionsEditorActivity"
                        )
                        putExtra("extra_pkgname", packageName)
                    }
                    startActivity(intent)
                } else {
                    startActivity(
                        Intent(
                            "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                            Uri.parse("package:$packageName")
                        )
                    )
                }
                isPermission = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            if (isPermission) {
                isPermission = false
                if (checkOverlayPermission(this)) {
                    Data.setServiceBatter(this, true)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun showDialogSetWallpaper(wallpaper: Bitmap?) {
        val dialog = Dialog(this@ApplyAnimationActivity)
        val bindingDialogWallPaper = LayoutWalpaperSelectOptionBinding.inflate(layoutInflater)

        dialog.setContentView(bindingDialogWallPaper.root)
        dialog.show()
        val w = (this.resources.displayMetrics.widthPixels * 0.9).toInt()
        val h: Int = ViewGroup.LayoutParams.WRAP_CONTENT
        if (dialog.window != null) {
            dialog.window?.setLayout(w, h)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }


        bindingDialogWallPaper.llLockScreen.tap {
            try {
                dialog.dismiss()
                WallPaperSetUp.setWallpaperLockScreen(wallpaper, this@ApplyAnimationActivity)
                val intent = Intent(this@ApplyAnimationActivity, SuccessActivity::class.java)
                startActivity(intent)
                isClicked = false
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
        bindingDialogWallPaper.llHomeScreen.tap {
            try {
                dialog.dismiss()
                WallPaperSetUp.setWallpaperHomeScreen(wallpaper, this@ApplyAnimationActivity)
                val intent = Intent(this@ApplyAnimationActivity, SuccessActivity::class.java)
                startActivity(intent)
                isClicked = false
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }

        bindingDialogWallPaper.llBoth.tap {
            try {
                dialog.dismiss()
                WallPaperSetUp.setWallpaperBoth(wallpaper, this@ApplyAnimationActivity)
                val intent = Intent(this@ApplyAnimationActivity, SuccessActivity::class.java)
                startActivity(intent)
                isClicked = false
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }


    fun done() {
        SharePrefUtils.increaseCountOpenApp2(this)
        SharePrefUtils.checkRate = true
        val intent = Intent(baseContext, SuccessActivity::class.java)
        startActivity(intent)
    }

    private fun getBitmap(path: String?, onClicked: OnClickedListener) {
        val requestOptions: RequestOptions = RequestOptions()
            .dontTransform()
            .override(Target.SIZE_ORIGINAL)

        Glide.with(this)
            .asBitmap()
            .load(path)
            .apply(requestOptions)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    onClicked.onClicked(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })

    }

    interface OnClickedListener {
        fun onClicked(bitmap: Bitmap?)
    }

}