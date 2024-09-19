package com.charging.animation.mobile.battery.activity.language

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.base.BaseViewModel
import com.charging.animation.mobile.battery.activity.language.model.LanguageModel
import com.charging.animation.mobile.battery.util.SharedPreferencesRepository
import com.charging.animation.mobile.battery.util.SystemUtil
import java.util.Locale

class LanguageViewModel : BaseViewModel() {
    val languages = MutableLiveData<List<LanguageModel>>()
    val selectedLanguage = MutableLiveData<LanguageModel>()



    private val _langDevice = MutableLiveData<String>()
    val langDevice: LiveData<String>
        get() = _langDevice

    private val _codeLang = MutableLiveData<String>()
    val codeLang: LiveData<String>
        get() = _codeLang

    fun first(context: Context){

        var langDevice = "en"
        var codeLang = "en"

        var position = 0
        var isLangDefault = false
        val listLanguage = mutableListOf<LanguageModel>()

        listLanguage.add(LanguageModel("English", "en", false, R.drawable.new_language_en))
        listLanguage.add(LanguageModel("Hindi", "hi", false, R.drawable.new_language_hi))
        listLanguage.add(LanguageModel("Spanish", "es", false, R.drawable.new_language_es))
        listLanguage.add(LanguageModel("French", "fr", false, R.drawable.new_language_fr))
        listLanguage.add(LanguageModel("Portuguese", "pt", false, R.drawable.new_language_pt))
        listLanguage.add(LanguageModel("Indonesian", "in", false, R.drawable.new_language_in))
        listLanguage.add(LanguageModel("German", "de", false, R.drawable.new_language_de))

        if (SharedPreferencesRepository.getBoolean(context, SharedPreferencesRepository.LANGUAGE, false)) {

            langDevice = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val locale: Locale =  Resources.getSystem().configuration.locales[0]
                locale.language
            } else {
                "en"
            }


            for ((index, languageModel) in listLanguage.withIndex()) {
                if (languageModel.code.contains(langDevice.toLowerCase())) {
                    isLangDefault = true
                    position = index
                    break
                }
            }

            if (position > 0 && isLangDefault) {
                val languageModel = listLanguage[position]
                listLanguage.removeAt(position)
                listLanguage.add(0, languageModel)
            }


            var check = -1
            for (i in listLanguage.indices) {
                val languageModel = listLanguage[i]
                if (languageModel.code.contains(SystemUtil.getPreLanguage(context))) {
                    listLanguage[i].active = true
                    listLanguage.remove(languageModel)
                    listLanguage.add(0, languageModel)
                    check = i
                    break
                }
            }
            listLanguage[0].active = true
            codeLang = listLanguage[0].code
        } else {
            langDevice = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val locale: Locale =  Resources.getSystem().configuration.locales[0]
                locale.language
            } else {
                "en"
            }

            for ((index, languageModel) in listLanguage.withIndex()) {
                if (languageModel.code.contains(langDevice.lowercase(Locale.getDefault()))) {
                    isLangDefault = true
                    position = index
                    break
                }
            }

            if (position > 0 && isLangDefault) {
                val languageModel = listLanguage[position]
                listLanguage.removeAt(position)
                listLanguage.add(0, languageModel)
            }

            listLanguage[0].active = true
            codeLang = listLanguage[0].code

        }

        _langDevice.postValue(langDevice)
        _codeLang.postValue(codeLang)
        languages.postValue(listLanguage)
    }

    fun setSelectedLanguage(language: LanguageModel) {
        selectedLanguage.value = language
        _codeLang.postValue(language.code)
    }
}