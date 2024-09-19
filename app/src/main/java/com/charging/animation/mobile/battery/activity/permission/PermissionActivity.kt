package com.charging.animation.mobile.battery.activity.permission

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.databinding.ActivityPermnissionBinding
import com.charging.animation.mobile.battery.util.SystemUtil
import com.charging.animation.mobile.battery.custom.view.tap
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.*

class PermissionActivity : BaseActivity<ActivityPermnissionBinding, PermissionViewModel>() {
    private var checkStorage = false

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

            binding.blurView.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25f)

            binding.blurViewPermission.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25f)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityPermnissionBinding =
        ActivityPermnissionBinding.inflate(layoutInflater)

    override fun setViewModel(): PermissionViewModel = viewModels<PermissionViewModel>().value
    override fun init() {
        CommonAds.logEvent(this, "permission_open")
        blur()

        try {
            binding.imgBg.loadImage(this,R.drawable.permission_bg, binding.imgBg, true)
            binding.imageView2.loadImage(this,R.drawable.permission_ic_lock, binding.imageView2)
        }catch (e: Exception){
            e.printStackTrace()
        }

        dialog = Dialog(this@PermissionActivity)

        binding.swStore.tap {
            CommonAds.logEvent(this, "permission_allow_click")
            if (!checkStorage) {
                checkAndRequestPermissions()
            }
        }

        binding.imgNext.tap {
            nextActivity()
        }


    }


    override fun listener() {

    }

    override fun viewModel() {

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val readMediaPermission = Manifest.permission.READ_MEDIA_VIDEO

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val readMediaPermission2 = Manifest.permission.READ_MEDIA_IMAGES

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val readMediaPermission3 = Manifest.permission.READ_MEDIA_AUDIO

    private val readMediaPermission4 = Manifest.permission.READ_EXTERNAL_STORAGE
    private val readMediaPermission5 = Manifest.permission.WRITE_EXTERNAL_STORAGE


    private fun requestStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsResult.launch(
                arrayOf(
                    readMediaPermission,
                    readMediaPermission2,
                    readMediaPermission3
                )
            )
        } else {
            permissionsResult.launch(arrayOf(readMediaPermission5, readMediaPermission4))
        }
    }


    private val permissionsResult: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (permissions.values.all { it }) {
                    CommonAds.logEvent(this@PermissionActivity, "permission_allowed")
                    checkStorage = true
                    binding.swStore.setImageResource(R.drawable.ic_switch_on)
                    binding.frAds.visibility =
                        if (SystemUtil.isNetworkConnected(baseContext)) View.VISIBLE else View.INVISIBLE
                    if (!getSharedPreferences(
                            "REMOTE",
                            MODE_PRIVATE
                        ).getBoolean("native_permission", CommonAds.native_permission)
                    ) {
                        binding.frAds.visibility = View.INVISIBLE
                    }
                } else {
                    binding.swStore.setImageResource(R.drawable.switch_off)
                    dialogForPermission()
                }
            }
        }

    private fun nextActivity() {
        CommonAds.logEvent(this, "permission_continue_click")
        startActivity(Intent(this@PermissionActivity, MainActivity::class.java))
    }

    private fun checkAndRequestPermissions() {
        binding.frAds.visibility = View.INVISIBLE
        requestStorage()
    }


    var dialog: Dialog? = null
    private fun dialogForPermission() {
        dialog?.setContentView(R.layout.dialog_overlay_permisson)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        (dialog?.findViewById<View>(R.id.cbutton) as Button).setText(R.string.go_to_settings)
        (dialog?.findViewById<View>(R.id.tv_title) as TextView).setText(R.string.enable_permission)
        binding.frAds.visibility = View.INVISIBLE
        dialog?.show()
        dialog?.setOnDismissListener {
            if (SystemUtil.isNetworkConnected(baseContext)) {
                binding.frAds.visibility = View.VISIBLE
            } else {
                binding.frAds.visibility = View.INVISIBLE
            }
            if (!getSharedPreferences("REMOTE", MODE_PRIVATE).getBoolean(
                    "native_permission",
                    CommonAds.native_permission
                )
            ) {
                binding.frAds.visibility = View.INVISIBLE
            }
        }
        (dialog?.findViewById<View>(R.id.cbutton) as Button).setOnClickListener { view: View? ->
            dialog?.dismiss()
            val intent =
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
            binding.frAds.visibility = View.VISIBLE
        }
    }


    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_VIDEO
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                checkStorage = true
                binding.swStore.setImageResource(R.drawable.ic_switch_on)
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                checkStorage = true
                binding.swStore.setImageResource(R.drawable.ic_switch_on)
            }
        }

    }

}