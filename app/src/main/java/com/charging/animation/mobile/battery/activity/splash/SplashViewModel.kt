package com.charging.animation.mobile.battery.activity.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.charging.animation.mobile.battery.activity.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress

    private val _countdownFinished = MutableLiveData<Boolean>()
    val countdownFinished: LiveData<Boolean> = _countdownFinished

    private val countDownTimer: CountDownTimer = object : CountDownTimer(2500, 5) {
        override fun onTick(millisUntilFinished: Long) {
            val progressValue = ((2500 - millisUntilFinished) * 100 / 2500).toInt()
            viewModelScope.launch(Dispatchers.Main) {
                _progress.postValue(progressValue)
            }
        }

        override fun onFinish() {
            viewModelScope.launch(Dispatchers.Main) {
                _progress.postValue(100)
                _countdownFinished.postValue(true)
            }
        }
    }

    init {
        _progress.value = 0
    }

    fun startCountdown() {
        countDownTimer.start()
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }

}