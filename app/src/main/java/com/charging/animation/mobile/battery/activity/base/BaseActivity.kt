package com.charging.animation.mobile.battery.activity.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.charging.animation.mobile.battery.util.SystemUtil


@Suppress("DEPRECATION")
abstract class BaseActivity<T : ViewBinding, V : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUtil.setLocale(this)
        setContentView(getInflatedLayout(layoutInflater))
        viewModel = setViewModel()
        init()
        listener()
        viewModel()
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): T
    abstract fun setViewModel(): V
    private fun getInflatedLayout(inflater: LayoutInflater): View {
        binding = setBinding(inflater)
        return binding.root
    }

    abstract fun init()
    abstract fun listener()
    abstract fun viewModel()

    override fun onStart() {
        super.onStart()
        fullScreen(this)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        fullScreen(this)
    }

    private fun fullScreen(activity: Activity?) {
        try {
            if (activity == null) return
            val window: Window = activity.window ?: return
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
            val lp = window.attributes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
            window.attributes = lp
        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    fun showActivity(activity: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(this, activity)
        bundle?.let {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    fun showActivityFinishAffinity(activity: Class<*>, bundle: Bundle? = null) {
        finishAffinity()
        showActivity(activity, bundle)
    }

    fun showActivityFinish(activity: Class<*>, bundle: Bundle? = null) {
        showActivity(activity, bundle)
        finish()
    }

}