package com.charging.animation.mobile.battery.fragment.alarm;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.bumptech.glide.Glide;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseFragment;
import com.charging.animation.mobile.battery.activity.ring.RingtoneActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.databinding.FragmentAlarmBinding;
import com.charging.animation.mobile.battery.receiver.AnimationReceiver;
import com.charging.animation.mobile.battery.service.FullBatterService;
import com.charging.animation.mobile.battery.service.HighService;
import com.charging.animation.mobile.battery.service.LowService;
import com.charging.animation.mobile.battery.util.Data;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0011H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/alarm/AlarmFragment;", "Lcom/charging/animation/mobile/battery/activity/base/BaseFragment;", "Lcom/charging/animation/mobile/battery/databinding/FragmentAlarmBinding;", "Lcom/charging/animation/mobile/battery/fragment/alarm/AlarmViewModel;", "()V", "isHigh", "", "isLow", "isService", "receiver", "Landroid/content/BroadcastReceiver;", "getReceiver", "()Landroid/content/BroadcastReceiver;", "setReceiver", "(Landroid/content/BroadcastReceiver;)V", "getViewBinding", "init", "", "listener", "onDestroy", "onResume", "setViewModel", "status", "n", "", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class AlarmFragment extends com.charging.animation.mobile.battery.activity.base.BaseFragment<com.charging.animation.mobile.battery.databinding.FragmentAlarmBinding, com.charging.animation.mobile.battery.fragment.alarm.AlarmViewModel> {
    private boolean isHigh = false;
    private boolean isService = false;
    private boolean isLow = false;
    @org.jetbrains.annotations.Nullable
    private android.content.BroadcastReceiver receiver;
    
    public AlarmFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.FragmentAlarmBinding getViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.fragment.alarm.AlarmViewModel setViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void init() {
    }
    
    @java.lang.Override
    public void listener() {
    }
    
    @java.lang.Override
    public void viewModel() {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.content.BroadcastReceiver getReceiver() {
        return null;
    }
    
    public final void setReceiver(@org.jetbrains.annotations.Nullable
    android.content.BroadcastReceiver p0) {
    }
    
    public final void status(int n) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
}