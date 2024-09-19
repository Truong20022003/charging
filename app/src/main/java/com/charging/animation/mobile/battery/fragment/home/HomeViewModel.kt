package com.charging.animation.mobile.battery.fragment.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.charging.animation.mobile.battery.activity.base.BaseViewModel
import com.charging.animation.mobile.battery.fragment.home.listener.Listener
import com.charging.animation.mobile.battery.fragment.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success
    fun api(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            HomeRepository.getAllApi(context, object : Listener(){
                override fun onSuccess() {
                    super.onSuccess()
                    viewModelScope.launch(Dispatchers.Main) {
                        _success.value = true
                    }

                }

                override fun onFail() {
                    super.onFail()
                    viewModelScope.launch(Dispatchers.Main) {
                        _success.value = false
                    }

                }
            })
        }
    }
}