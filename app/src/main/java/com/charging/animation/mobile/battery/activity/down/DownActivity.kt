package com.charging.animation.mobile.battery.activity.down

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.down.dialog.ProcessingDialog
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.database.Database
import com.charging.animation.mobile.battery.databinding.ActivityDownUpdateBinding
import com.charging.animation.mobile.battery.dialog.DialogWatchAds
import com.charging.animation.mobile.battery.listener.Listener
import com.charging.animation.mobile.battery.util.Constants
import com.charging.animation.mobile.battery.util.Data
import com.charging.animation.mobile.battery.util.SystemUtil
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import java.io.File

@Suppress("DEPRECATION")
class DownActivity : BaseActivity<ActivityDownUpdateBinding, DownViewModel>() {
    var name: String? = null
    var path: String? = null
    var results: Results? = null
    var dialog: ProcessingDialog? = null


    override fun setBinding(layoutInflater: LayoutInflater): ActivityDownUpdateBinding =
        ActivityDownUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): DownViewModel = viewModels<DownViewModel>().value

    override fun init() {
        CommonAds.logEvent(this@DownActivity, "item_download_view")
        blur()

        binding.imgBtnDown.loadImage(this, R.drawable.dowm_bg_btn, binding.imgBtnDown)
        binding.imgCreator.loadImage(this, R.drawable.dowm_bg_creator, binding.imgCreator)

        dialog = ProcessingDialog(this@DownActivity)
        results = intent.getSerializableExtra("link") as Results?
        if (results?.link?.contains(".gif") == true || results?.link?.contains(".GIF") == true) {
            Glide.with(this).asGif().load(results?.link).into(binding.gitAnim)
        } else {
            Glide.with(this).load(results?.link).into(binding.gitAnim)
        }
        receiver()
    }

    override fun listener() {
        binding.imgBack.tap {
            if (!DOWN) {
                onBackPressed()
            } else {
                Toast.makeText(
                    baseContext,
                    baseContext.getString(R.string.downing),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnDown.tap {
            CommonAds.logEvent(this@DownActivity, "item_download_click")
            name = results?.name
//            if (results?.free == true) {
//                startDownload(results?.link ?: "")
//            } else {
//                show(results)
//            }
            dialog?.show()
            startDownload(results?.link ?: "")
        }
    }

    override fun viewModel() {

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

            binding.blurViewMain.setupWith(binding.root as ViewGroup,  getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)

            binding.blurViewBtn.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)

            binding.blurViewCreator.setupWith(binding.root as ViewGroup, getBlurAlgorithm())
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25F)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun receiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                broadcastReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                RECEIVER_EXPORTED
            )
        } else {
            registerReceiver(
                broadcastReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            )
        }
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
                try {
                    dialog?.dismiss()
                    val file = File(path ?: "")
                    if (file.exists()) {
                        DOWN = false
                        Database.getInstance(context).resultsDao().insert(Results(0, path, 2, results?.folder))
                        LocalBroadcastManager.getInstance(this@DownActivity).sendBroadcast(Intent(Constants.ACTION_DOWN).putExtra(Constants.ACTION_DOWN, Results(0, path, 2, results?.folder, true)))
                        startActivity(Intent(this@DownActivity, ApplyAnimationActivity::class.java).putExtra(Constants.BUNDLE_LINK, path).putExtra(Constants.BUNDLE_TYPE, 2))
                        path = ""
                        finish()
                    } else {
                        DOWN = false
                    }
                } catch (exception: Exception) {
                    DOWN = false
                }
            }
        }
    }

    private fun startDownload(link: String) {
        val request = DownloadManager.Request(Uri.parse(link))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setTitle("Charging down!")
        request.setDescription("Download data...")
        request.setAllowedOverMetered(true)
        request.setAllowedOverRoaming(true)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        val f = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString()
        )
        if (!f.exists()) f.mkdir()
        var file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/${Constants.FOLDER_CHANGING_ANIMATION}" + name
        )
        if (file.exists()) file.delete()
        file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString() + "/${Constants.FOLDER_CHANGING_ANIMATION}" + name
        )
        request.setDestinationUri(Uri.fromFile(file))
        path = file.absolutePath
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun show(results: Results?) {
        if (getSharedPreferences("REMOTE", MODE_PRIVATE).getBoolean("rewarded_animation", CommonAds.rewarded_animation)) {
            if (Data.getADS(baseContext)) {
                showAds(results)
            } else {
                val dialogWatchAds = DialogWatchAds(this, object : Listener() {
                    override fun onClick(ads: Boolean) {
                        super.onClick(ads)
                        showAds(results)
                    }
                }, this)
                binding.frAds.visibility = View.GONE
                dialogWatchAds.setOnDismissListener {
                    if (SystemUtil.isNetworkConnected(baseContext)) {
                        binding.frAds.visibility = View.VISIBLE
                    } else {
                        binding.frAds.visibility = View.GONE
                    }
                    if (!getSharedPreferences("REMOTE", MODE_PRIVATE).getBoolean(
                            "native_preview",
                            CommonAds.native_preview
                        )
                    ) {
                        binding.frAds.visibility = View.GONE
                    }
                }
                dialogWatchAds.show()
            }
        } else {
            startDownload(results?.link ?: "")
        }
    }

    fun showAds(results: Results?) {
        if (!DOWN) {
            DOWN = true
            val link1 = results?.link
            name = results?.name
            startDownload(link1 ?: "")
        } else {
            Toast.makeText(baseContext, baseContext.getString(R.string.downing), Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        var DOWN = false
    }
}