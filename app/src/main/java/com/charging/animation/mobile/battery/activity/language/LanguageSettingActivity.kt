package com.charging.animation.mobile.battery.activity.language

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.charging.animation.mobile.battery.activity.base.BaseActivity
import com.charging.animation.mobile.battery.activity.language.model.LanguageModel
import com.charging.animation.mobile.battery.activity.main.MainActivity
import com.charging.animation.mobile.battery.custom.view.tap
import com.charging.animation.mobile.battery.databinding.ActivityLanguageSettingUpdateBinding
import com.charging.animation.mobile.battery.util.SharedPreferencesRepository
import com.charging.animation.mobile.battery.util.SystemUtil

class LanguageSettingActivity : BaseActivity<ActivityLanguageSettingUpdateBinding, LanguageViewModel>() {
    private lateinit var languageAdapter: LanguageStartAdapter
    var langDevice = "en"
    var codeLang = "en"
    override fun setBinding(layoutInflater: LayoutInflater): ActivityLanguageSettingUpdateBinding =
        ActivityLanguageSettingUpdateBinding.inflate(layoutInflater)

    override fun setViewModel(): LanguageViewModel = viewModels<LanguageViewModel>().value

    override fun init() {

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

    override fun listener() {
        binding.imgSave.tap {
            SystemUtil.saveLocale(baseContext, codeLang)
            SharedPreferencesRepository.setBoolean(this@LanguageSettingActivity, SharedPreferencesRepository.LANGUAGE, true)
            showActivityFinishAffinity(MainActivity::class.java)
        }

        binding.imgBack.tap {
            finish()
        }
    }

    override fun viewModel() {

    }

}