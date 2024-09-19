package com.charging.animation.mobile.battery.activity.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.info.InfoActivity
import com.charging.animation.mobile.battery.activity.main.adapter.BottomAdapter
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.database.Database
import com.charging.animation.mobile.battery.databinding.ActivityMainBinding
import com.charging.animation.mobile.battery.dialog.ExitDialog
import com.charging.animation.mobile.battery.dialog.RatingDialog
import com.charging.animation.mobile.battery.dialog.RatingDialog.OnPress
import com.charging.animation.mobile.battery.listener.Listener
import com.charging.animation.mobile.battery.util.RealPathUtil
import com.charging.animation.mobile.battery.util.SharePrefUtils
import com.charging.animation.mobile.battery.util.SystemUtil
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur

@Suppress("DEPRECATION")
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private var bottomAdapter: BottomAdapter? = null
    private var checkGallery = false
    private var checkAlarm = false
    private var count = 0
    private var alertDialog: AlertDialog? = null

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setViewModel(): MainViewModel =  viewModels<MainViewModel>().value

    override fun init() {
        SharePrefUtils.inter_intro2(this)
        loadFragment()
        binding.btnHome.isEnabled = false
        binding.btnGallery.isEnabled = false
        binding.btnSetting.isEnabled = false
        blur()
        dialogSetting()
    }

    private fun dialogSetting(){
        alertDialog = AlertDialog.Builder(this).create()

        alertDialog?.setTitle("Grant Permission")
        alertDialog?.setCancelable(false)
        alertDialog?.setMessage("Please grant all permissions to access additional functionality.")
        alertDialog?.setButton(-1, "Go to setting" as CharSequence) { _: DialogInterface?, _: Int ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri =
                Uri.fromParts("package", applicationContext.packageName, null)
            intent.data = uri
            startActivity(intent)
            alertDialog?.dismiss()
            checkPer = true
        }
        binding.viewpager.offscreenPageLimit = 3
    }

    override fun listener() {
        binding.imgBack.tap {
            startSetting()
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("setting"))
        }

        binding.btnHome.tap {
            binding.viewpager.currentItem = 0
        }

        binding.btnGallery.tap {
            CommonAds.logEvent(this@MainActivity, "home_gallery_click")
            binding.viewpager.currentItem = 1
        }
        binding.btnSetting.tap {
            CommonAds.logEvent(this@MainActivity, "home_settings_click")
            checkAlarm = false
            binding.viewpager.currentItem = 2
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("setting"))
        }

        binding.btnInfo.tap {
            CommonAds.logEvent(this, "home_info_click")
            startActivity(Intent(this, InfoActivity::class.java))
        }

        binding.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(positionOffset == 0F && positionOffsetPixels == 0){
                    when (position) {
                        0 -> {
                            binding.imgBack.visibility = View.INVISIBLE
                            SystemUtil.setLocale(this@MainActivity)
                            changeMenu()
                            binding.tv1.visibility = View.VISIBLE
                            binding.img1.setImageResource(R.drawable.main_ic_home2)
                            binding.tvTitle.text = getString(R.string.battery_charging2)
                            textGradient()
                        }
                        1 -> {
                            binding.imgBack.visibility = View.INVISIBLE
                            SystemUtil.setLocale(this@MainActivity)
                            changeMenu()
                            binding.tv3.visibility = View.VISIBLE
                            binding.img3.setImageResource(R.drawable.main_ic_gallery2)
                            binding.tvTitle.text = getString(R.string.gallery)
                            textGradient()
                        }
                        2 -> {
                            SystemUtil.setLocale(this@MainActivity)
                            changeMenu()
                            binding.tv4.visibility = View.VISIBLE
                            binding.img4.setImageResource(R.drawable.main_ic_setting2)
                            if (!checkAlarm) {
                                binding.tvTitle.text = getString(R.string.settings)
                            } else {
                                binding.tvTitle.text = getString(R.string.alarm)
                            }
                            textGradient()
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun viewModel() {
        
    }

    private fun textGradient() {
        val paint = binding.tvTitle.paint
        val width = paint.measureText(binding.tvTitle.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, binding.tvTitle.textSize, intArrayOf(
                Color.parseColor("#F16CFF"),
                Color.parseColor("#12D6F0"),
            ), null, Shader.TileMode.CLAMP
        )
        binding.tvTitle.paint.shader = textShader
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
            val algorithm: BlurAlgorithm = getBlurAlgorithm()

            binding.blurViewMain.setupWith(binding.root as ViewGroup, algorithm)
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    
    private fun loadFragment(){
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                fragment()
            } else {
                requestStorage()
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                fragment()
            } else {
                requestStorage()
            }
        }
    }

    fun fragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            bottomAdapter = BottomAdapter(supportFragmentManager, 1)
            binding.viewpager.adapter = bottomAdapter
            binding.viewpager.offscreenPageLimit = 4
            binding.btnHome.isEnabled = true
            binding.btnGallery.isEnabled = true
            binding.btnSetting.isEnabled = true
            binding.progress.visibility = View.GONE
        }, 100)
    }

    fun home() {
        binding.viewpager.setCurrentItem(0, true)
    }

    @SuppressLint("ResourceAsColor")
    fun changeMenu() {
        binding.tv1.visibility = View.GONE
        binding.tv3.visibility = View.GONE
        binding.tv4.visibility = View.GONE
        binding.img1.setImageResource(R.drawable.main_ic_home)
        binding.img3.setImageResource(R.drawable.main_ic_gallery)
        binding.img4.setImageResource(R.drawable.main_ic_setting)
        binding.btnSetting.setCardBackgroundColor(resources.getColor(android.R.color.transparent))
        binding.btnHome.setCardBackgroundColor(resources.getColor(android.R.color.transparent))
        binding.btnGallery.setCardBackgroundColor(resources.getColor(android.R.color.transparent))
    }


    fun select() {
        if (permission()) {
            if (!checkGallery) {
                checkGallery = true
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1234)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkGallery) {
            checkGallery = false
        }
        if (checkPer) {
            checkPer = false
            setUpUi()
        }
        if (SharePrefUtils.checkRate) {
            SharePrefUtils.checkRate = false
            if (!SharePrefUtils.isRated(this)) {
                val count = SharePrefUtils.getCountOpenApp2(this)
                if (count == 3 || count == 5 || count == 7) {
                    showRateDialog(false)
                }
            }
        }

        if (SharePrefUtils.getSampleInt(this@MainActivity) == 1) {
            showDialogBatteryNotification()
        }
    }

    private fun setUpUi() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                fragment()
            } else {
                if (alertDialog?.isShowing == false) {
                    alertDialog?.show()
                }
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                fragment()
            } else {
                if (alertDialog?.isShowing == false) {
                    alertDialog?.show()
                }
            }
        }
    }

    private fun dialogExit() {
        ExitDialog(this@MainActivity, object : Listener() {
            override fun onExit() {
                super.onExit()
                SharePrefUtils.increaseCountOpenApp(this@MainActivity)
                finish()
            }

            override fun onRate() {
                super.onRate()
                showRateDialog(true)
            }

        }, this@MainActivity).show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        dialogExit()
    }

    private fun requestStorage() {
        count++
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsResult.launch(
                arrayOf(
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_AUDIO,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            )
        } else {
            permissionsResult.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    private val permissionsResult = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (permissions.values.all { it }) {
                    fragment()
                    alertDialog?.dismiss()
                } else {
                    checkPer = true
                    if (count >= 1) {
                        if (alertDialog?.isShowing == false) {
                            alertDialog?.show()
                        }
                    } else {
                        requestStorage()
                    }
                }
            } else {
                alertDialog?.dismiss()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private var checkPer = false
    fun permission(): Boolean {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            ) {

                return true
            } else {
                requestStorage()
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                requestStorage()
            }
        }
        return false
    }





    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
            if (data != null) {
                val path = RealPathUtil.getRealPath(baseContext, data.data)
                val results = Results(0, path, 3, "Gallery")
                Database.getInstance(baseContext).resultsDao().insert(results)
                startActivity(Intent(baseContext, ApplyAnimationActivity::class.java).putExtra("link", results.link).putExtra("type", 3))

                LocalBroadcastManager.getInstance(this@MainActivity).sendBroadcast(Intent("gallery").putExtra("gallery", results))
                try {
                    if (Build.VERSION.SDK_INT >= 33) {
                        val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        data.data?.let { this@MainActivity.contentResolver.takePersistableUriPermission(it, flag) }
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        }
    }

    fun showRateDialog(exit: Boolean) {
        CommonAds.logEvent(this@MainActivity, "rate_show", "position", "main")
        val manager = ReviewManagerFactory.create(this@MainActivity)
        val ratingDialog = RatingDialog(this@MainActivity)
        ratingDialog.init(this@MainActivity, object : OnPress {
            override fun send(s: Int) {
                CommonAds.logEvent(
                    this@MainActivity,
                    "rate_submit",
                    "rate_star",
                    s.toString() + "_star"
                )
                ratingDialog.dismiss()
                SharePrefUtils.forceRated(baseContext)
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.thank_you_for_rating),
                    Toast.LENGTH_SHORT
                ).show()
                if (exit) {
                    finish()
                } else {
                    ratingDialog.dismiss()
                }
            }

            override fun rating(s: Int) {
                CommonAds.logEvent(
                    this@MainActivity,
                    "rate_submit",
                    "rate_star",
                    s.toString() + "_star"
                )
                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { task: Task<ReviewInfo?> ->
                    if (task.isSuccessful) {
                        SharePrefUtils.forceRated(this@MainActivity)
                    }
                    if (exit) {
                        finish()
                    } else {
                        ratingDialog.dismiss()
                    }
                }
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }

            override fun cancel() {
                CommonAds.logEvent(this@MainActivity, "rate_not_now")
                if (exit) {
                    finish()
                } else {
                    ratingDialog.dismiss()
                }
            }

            override fun later() {
                CommonAds.logEvent(this@MainActivity, "rate_not_now")
                if (exit) {
                    finish()
                } else {
                    ratingDialog.dismiss()
                }
            }
        })
        ratingDialog.show()
    }

    private fun showDialogBatteryNotification() {

        val dialog = Dialog(this@MainActivity)
        dialog.setCancelable(true)
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        }catch (e : Exception){
            e.printStackTrace()
        }
        SystemUtil.setLocale(this)
        SharePrefUtils.setSampleInt(this)

        dialog.setContentView(R.layout.dialog_battery_notification)
        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        CommonAds.logEvent(this@MainActivity, "home_noti")

        val cancel = dialog.findViewById<ConstraintLayout>(R.id.btnCancel)
        val goToAlarm = dialog.findViewById<ConstraintLayout>(R.id.btnApply)

        cancel.setOnClickListener {
            dialog.dismiss()
        }

        goToAlarm.setOnClickListener {
            CommonAds.logEvent(this@MainActivity, "home_noti_alarm_setting")
            checkAlarm = true
            binding.viewpager.currentItem = 2
            binding.img4.setImageResource(R.drawable.main_ic_setting2)
            startAlarm()
            changeMenu()
            binding.tv4.visibility = View.VISIBLE
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("alarm"))
            dialog.dismiss()
        }
        dialog.show()
    }

    fun startAlarm() {
        binding.imgBack.visibility = View.VISIBLE
        binding.tvTitle.text = getString(R.string.alarm)

    }

    fun startSetting() {
        binding.imgBack.visibility = View.INVISIBLE
        binding.tvTitle.text = getString(R.string.settings)
    }
}