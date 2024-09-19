package com.charging.animation.mobile.battery.activity.tutorial

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.activity.permission.PermissionActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.databinding.ActivityTutorialBinding
import com.charging.animation.mobile.battery.model.TutorialModel
import com.charging.animation.mobile.battery.util.Data
import com.charging.animation.mobile.battery.util.SharePrefUtils
import com.charging.animation.mobile.battery.custom.view.tap
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur

class TutorialActivity : BaseActivity<ActivityTutorialBinding, TutorialViewModel>() {
    var list: MutableList<TutorialModel> = mutableListOf()
    var adapter: TutorialAdapter? = null
    private var check = 0

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

            binding.blurView.setupWith(binding.root as ViewGroup, algorithm)
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(25f)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun recycleView() {
        list.add(
            TutorialModel(
                R.drawable.img_tutorial_1,
                getString(R.string.tutorial_title_1).replace(".", ""),
                getString(R.string.intro_1)
            )
        )
        list.add(
            TutorialModel(
                R.drawable.img_tutorial_2,
                getString(R.string.charging_nalarm).replace(".", ""),
                getString(R.string.intro_2)
            )
        )
        list.add(
            TutorialModel(
                R.drawable.img_tutorial_4,
                getString(R.string.the_information).replace(".", ""),
                getString(R.string.intro_3)
            )
        )

        adapter = TutorialAdapter(list, this)
        binding.viewpager.adapter = adapter
        binding.viewpager.offscreenPageLimit = 3
        binding.viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        dotSelect()
                        CommonAds.logEvent(this@TutorialActivity, "Onboarding1_next_view")
                        binding.tvTile.text = list[position].title
                        binding.tvContent.text = list[position].content
                        check = -1
                        binding.imgPre.visibility = View.INVISIBLE
                    }

                    1 -> {
                        dotSelect()
                        CommonAds.logEvent(this@TutorialActivity, "Onboarding2_next_view")
                        binding.tvTile.text = list[position].title
                        binding.tvContent.text = list[position].content
                        check = 0
                        binding.imgPre.visibility = View.VISIBLE
                    }

                    2 -> {
                        dotSelect()
                        CommonAds.logEvent(this@TutorialActivity, "onboarding2_next_click")
                        binding.tvTile.text = list[position].title
                        binding.tvContent.text = list[position].content
                        check = 1
                        binding.imgPre.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityTutorialBinding =
        ActivityTutorialBinding.inflate(layoutInflater)

    override fun setViewModel(): TutorialViewModel = viewModels<TutorialViewModel>().value

    override fun init() {
        CommonAds.logEvent(this@TutorialActivity, "onboarding1_view")
        blur()
        recycleView()
    }

    override fun listener() {
        binding.imgNext.tap {
            when (binding.viewpager.currentItem) {
                0 -> {
                    CommonAds.logEvent(this@TutorialActivity, "onboarding1_next_click")
                }

                1 -> {
                    CommonAds.logEvent(this@TutorialActivity, "onboarding2_next_click")
                }

                else -> {
                    CommonAds.logEvent(this@TutorialActivity, "onboarding3_start_click")
                    start()
                }
            }
            if (binding.viewpager.currentItem < 3) {
                binding.viewpager.setCurrentItem(
                    binding.viewpager.currentItem + 1,
                    true
                )
            }
        }

        binding.imgPre.tap {
            when (binding.viewpager.currentItem) {
                0 -> {
                    CommonAds.logEvent(this@TutorialActivity, "onboarding1_next_click")
                }

                1 -> {
                    CommonAds.logEvent(this@TutorialActivity, "onboarding2_next_click")
                }
            }
            if (binding.viewpager.currentItem > 0) {
                binding.viewpager.setCurrentItem(
                    binding.viewpager.currentItem - 1,
                    true
                )
            }
        }
    }

    override fun viewModel() {

    }

    private fun dotSelect() {
        when (binding.viewpager.currentItem) {
            0 -> {
                binding.dot1.setBackgroundResource(R.drawable.dot_select)
                binding.dot2.setBackgroundResource(R.drawable.dot_not_select)
                binding.dot3.setBackgroundResource(R.drawable.dot_not_select)
            }

            1 -> {
                binding.dot1.setBackgroundResource(R.drawable.dot_select)
                binding.dot2.setBackgroundResource(R.drawable.dot_select)
                binding.dot3.setBackgroundResource(R.drawable.dot_not_select)
            }

            else -> {
                binding.dot1.setBackgroundResource(R.drawable.dot_select)
                binding.dot2.setBackgroundResource(R.drawable.dot_select)
                binding.dot3.setBackgroundResource(R.drawable.dot_select)
            }
        }
    }

    private fun start() {
        Data.setTutorial(applicationContext, true)
        checkLive()
        checkService()
        if (SharePrefUtils.inter_intro(this@TutorialActivity) >= 2) {
            startActivity(Intent(this@TutorialActivity, MainActivity::class.java))
        } else {
            startActivity(Intent(this@TutorialActivity, PermissionActivity::class.java))
        }
        finishAffinity()
    }


    private fun checkLive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val packageName = packageName
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            Data.setServiceLive(this, pm.isIgnoringBatteryOptimizations(packageName))
        }
    }

    private fun checkService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Data.setServiceBatter(this, Settings.canDrawOverlays(this))
        }
    }


    override fun onBackPressed() {
        finishAffinity()
    }
}