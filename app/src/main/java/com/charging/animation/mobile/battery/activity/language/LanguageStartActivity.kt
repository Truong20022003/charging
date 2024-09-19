package com.charging.animation.mobile.battery.activity.language

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseActivity

import com.charging.animation.mobile.battery.activity.language.model.LanguageModel
import com.charging.animation.mobile.battery.activity.tutorial.TutorialActivity
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.custom.view.loadImage
import com.charging.animation.mobile.battery.databinding.ActivityLanguageStartBinding
import com.charging.animation.mobile.battery.util.SharedPreferencesRepository
import com.charging.animation.mobile.battery.util.SystemUtil
import com.charging.animation.mobile.battery.custom.view.tap


class LanguageStartActivity : BaseActivity<ActivityLanguageStartBinding, LanguageViewModel>() {

    private lateinit var languageAdapter: LanguageStartAdapter
    var langDevice = "en"
    var codeLang = "en"

    private fun initView() {

        CommonAds.logEvent(this, "language_fo_open")

        val linearLayoutManager = LinearLayoutManager(this)
        languageAdapter =
            LanguageStartAdapter(this, mutableListOf(), object : LanguageStartAdapter.Listener() {
                override
                fun onClickLanguage(languageSelect: LanguageModel) {
                    viewModel.setSelectedLanguage(languageSelect)
                }
            })
        binding.rvLanguage.layoutManager = linearLayoutManager
        binding.rvLanguage.adapter = languageAdapter


        viewModel.languages.observe(this) { list ->
            languageAdapter.updateData(list)
        }

        viewModel.langDevice.observe(this) { langDevice ->
            this.langDevice = langDevice
        }

        viewModel.codeLang.observe(this) { codeLang ->
            this.codeLang = codeLang
        }

        viewModel.first(this)



        viewModel.selectedLanguage.observe(this) { selectedLanguage ->
            languageAdapter.setSelectedLanguage(selectedLanguage)
        }


    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityLanguageStartBinding =
        ActivityLanguageStartBinding.inflate(layoutInflater)

    override fun setViewModel(): LanguageViewModel = viewModels<LanguageViewModel>().value

    override fun init() {
        initView()
    }

    override fun listener() {
        binding.imgSave.tap {
            CommonAds.logEvent(this, "language_fo_save_click")
            SystemUtil.saveLocale(baseContext, codeLang)
            SharedPreferencesRepository.setBoolean(this@LanguageStartActivity, SharedPreferencesRepository.LANGUAGE, true)
            startActivity(Intent(baseContext, TutorialActivity::class.java))
            finishAffinity()
        }
    }

    override fun viewModel() {

    }
}