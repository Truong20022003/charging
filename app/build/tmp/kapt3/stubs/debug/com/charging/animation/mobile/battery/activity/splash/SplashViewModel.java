package com.charging.animation.mobile.battery.activity.splash;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.charging.animation.mobile.battery.activity.base.BaseViewModel;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0006\u0010\u0012\u001a\u00020\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/splash/SplashViewModel;", "Lcom/charging/animation/mobile/battery/activity/base/BaseViewModel;", "()V", "_countdownFinished", "Landroidx/lifecycle/MutableLiveData;", "", "_progress", "", "countDownTimer", "Landroid/os/CountDownTimer;", "countdownFinished", "Landroidx/lifecycle/LiveData;", "getCountdownFinished", "()Landroidx/lifecycle/LiveData;", "progress", "getProgress", "onCleared", "", "startCountdown", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class SplashViewModel extends com.charging.animation.mobile.battery.activity.base.BaseViewModel {
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _progress = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Integer> progress = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _countdownFinished = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> countdownFinished = null;
    private final android.os.CountDownTimer countDownTimer = null;
    
    public SplashViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getCountdownFinished() {
        return null;
    }
    
    public final void startCountdown() {
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
}