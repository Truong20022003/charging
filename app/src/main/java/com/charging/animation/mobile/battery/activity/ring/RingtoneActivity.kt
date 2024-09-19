package com.charging.animation.mobile.battery.activity.ring

import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivityRingtoneUpdateBinding
import com.charging.animation.mobile.battery.fragment.alarm.RingtoneAdapter
import com.charging.animation.mobile.battery.listener.Listener
import com.charging.animation.mobile.battery.model.Ringtone
import com.charging.animation.mobile.battery.util.Data

@Suppress("DEPRECATION")
class RingtoneActivity : BaseActivity<ActivityRingtoneUpdateBinding, RingtoneViewModel>() {
    private var adapter: RingtoneAdapter? = null
    private var list: List<Ringtone>? = null
    private var ringtone: Ringtone? = null
    private var mediaPlayer: MediaPlayer? = null
    private var alertDialog: AlertDialog? = null
    private var isPermission = false
    private var count = 0

    override fun setBinding(layoutInflater: LayoutInflater): ActivityRingtoneUpdateBinding = ActivityRingtoneUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): RingtoneViewModel = viewModels<RingtoneViewModel>().value
    override fun init() {
        ringtone = null
        alertDialog = AlertDialog.Builder(this).create()
        alertDialog?.setTitle("Grant Permission")
        alertDialog?.setCancelable(false)
        alertDialog?.setMessage("Please grant all permissions to access additional functionality.")
        alertDialog?.setButton(
            -1,
            "Go to setting" as CharSequence
        ) { _: DialogInterface?, _: Int ->
            isPermission = true
            alertDialog?.dismiss()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", applicationContext.packageName, null)
            intent.data = uri
            startActivity(intent)
        }
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getData()
            } else {
                requestStorage()
            }
        } else {
            getData()
        }
    }

    override fun listener() {
        binding.imgBack.tap { onBackPressed() }
        binding.btnBack3.tap {
            if (ringtone != null) {
                try {
                    Data.setSelectRingtone(this, ringtone?.uri)
                    Data.setSelectRingtoneName(this, ringtone?.name)
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                finish()
            } else {
                Toast.makeText(
                    baseContext,
                    getString(R.string.select) + " " + getString(R.string.ringtone),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun viewModel() {

    }

    private fun getData() {
        list = ArrayList()
        list = notifications
        adapter = RingtoneAdapter(list, this, object : Listener() {
            override fun onClick(ringtone2: Ringtone) {
                super.onClick(ringtone2)
                try {
                    ringtone = ringtone2
                    val notification = Uri.parse(ringtone?.uri)
                    if (mediaPlayer != null) {
                        if (mediaPlayer?.isPlaying == true) {
                            mediaPlayer?.stop()
                        }
                        mediaPlayer?.release()
                    }
                    mediaPlayer = MediaPlayer.create(baseContext, notification)
                    mediaPlayer?.start()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        })
        binding.rcvRingtone.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rcvRingtone.setHasFixedSize(true)
        binding.rcvRingtone.setItemViewCacheSize(100)
        binding.rcvRingtone.adapter = adapter


    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        finish()
    }
    override fun onResume() {
        super.onResume()
        if (isPermission) {
            isPermission = false
            loadUi()
        } else {
            try {
                val notification = Uri.parse(list?.get(0)?.uri ?: "")
                mediaPlayer = MediaPlayer.create(baseContext, notification)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
    override fun onStop() {
        super.onStop()
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
                mediaPlayer?.release()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        try {
            if (list != null && (list?.size ?: 0) > 0) {
                val notification = Uri.parse(list?.get(0)?.uri ?: "")
                mediaPlayer = MediaPlayer.create(baseContext, notification)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (mediaPlayer != null) {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
            }
            mediaPlayer?.release()
        }
    }
    private val notifications: List<Ringtone> get() {
        val manager = RingtoneManager(this)
        manager.setType(RingtoneManager.TYPE_RINGTONE)
        val cursor = manager.cursor
        val list: MutableList<Ringtone> = ArrayList()
        while (cursor.moveToNext()) {
            val notificationTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val notificationUri =
                cursor.getString(RingtoneManager.URI_COLUMN_INDEX) + "/" + cursor.getString(
                    RingtoneManager.ID_COLUMN_INDEX
                )
            Log.e("Ringtone", "Name: $notificationTitle")
            Log.e("Ringtone", "uri: $notificationUri")
            val duration = getSongDuration(notificationUri)
            val formattedDuration = formatDuration(duration)
            if (duration > 0) {
                list.add(Ringtone(notificationTitle, notificationUri, formattedDuration))
            }
        }
        return list
    }
    private fun getSongDuration(songUri: String?): Long {
        return try {
            val notification = Uri.parse(songUri)
            val mediaPlayer = MediaPlayer.create(applicationContext, notification)
            mediaPlayer.duration.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }
    private fun formatDuration(durationInMillis: Long): String {
        val seconds = durationInMillis / 1000 % 60
        val minutes = durationInMillis / (1000 * 60) % 60
        val hours = durationInMillis / (1000 * 60 * 60)
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    private fun loadUi() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_MEDIA_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getData()
                if (list != null && (list?.size ?: 0) > 0) {
                    val notification = Uri.parse(list?.get(0)?.uri ?: "")
                    mediaPlayer = MediaPlayer.create(baseContext, notification)
                }
            } else {
                if (alertDialog?.isShowing == false) {
                    alertDialog?.show()
                }
            }
        }
    }

    private fun requestStorage() {
        count++
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsResult.launch(arrayOf(Manifest.permission.READ_MEDIA_AUDIO))
        }
    }

    private val permissionsResult = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (permissions.values.all { it }) {
                    getData()
                    alertDialog?.dismiss()
                } else {
                    if (count >= 2) {
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

}