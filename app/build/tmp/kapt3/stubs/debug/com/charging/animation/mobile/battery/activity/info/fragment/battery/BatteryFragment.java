package com.charging.animation.mobile.battery.activity.info.fragment.battery;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseFragment;
import com.charging.animation.mobile.battery.databinding.FragmentBatteryBinding;
import com.charging.animation.mobile.battery.model.Battery;
import com.charging.animation.mobile.battery.receiver.AnimationReceiver;
import com.charging.animation.mobile.battery.util.Util;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0016J\u0006\u0010\u0010\u001a\u00020\u0018J\b\u0010\u001b\u001a\u00020\u0003H\u0016J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0018H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006#"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/info/fragment/battery/BatteryFragment;", "Lcom/charging/animation/mobile/battery/activity/base/BaseFragment;", "Lcom/charging/animation/mobile/battery/databinding/FragmentBatteryBinding;", "Lcom/charging/animation/mobile/battery/activity/info/fragment/battery/BatteryViewModel;", "()V", "battery", "Lcom/charging/animation/mobile/battery/model/Battery;", "getBattery", "()Lcom/charging/animation/mobile/battery/model/Battery;", "setBattery", "(Lcom/charging/animation/mobile/battery/model/Battery;)V", "isCharging", "", "()Z", "setCharging", "(Z)V", "receiver", "Landroid/content/BroadcastReceiver;", "getReceiver", "()Landroid/content/BroadcastReceiver;", "setReceiver", "(Landroid/content/BroadcastReceiver;)V", "getViewBinding", "init", "", "listener", "onDestroy", "setViewModel", "status", "n", "", "textGradient", "textView", "Landroid/widget/TextView;", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class BatteryFragment extends com.charging.animation.mobile.battery.activity.base.BaseFragment<com.charging.animation.mobile.battery.databinding.FragmentBatteryBinding, com.charging.animation.mobile.battery.activity.info.fragment.battery.BatteryViewModel> {
    @org.jetbrains.annotations.Nullable
    private com.charging.animation.mobile.battery.model.Battery battery;
    private boolean isCharging = false;
    @org.jetbrains.annotations.NotNull
    private android.content.BroadcastReceiver receiver;
    
    public BatteryFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.charging.animation.mobile.battery.model.Battery getBattery() {
        return null;
    }
    
    public final void setBattery(@org.jetbrains.annotations.Nullable
    com.charging.animation.mobile.battery.model.Battery p0) {
    }
    
    public final boolean isCharging() {
        return false;
    }
    
    public final void setCharging(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.FragmentBatteryBinding getViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.info.fragment.battery.BatteryViewModel setViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void init() {
    }
    
    public final void receiver() {
    }
    
    @java.lang.Override
    public void listener() {
    }
    
    @java.lang.Override
    public void viewModel() {
    }
    
    public final void textGradient(@org.jetbrains.annotations.NotNull
    android.widget.TextView textView) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.BroadcastReceiver getReceiver() {
        return null;
    }
    
    public final void setReceiver(@org.jetbrains.annotations.NotNull
    android.content.BroadcastReceiver p0) {
    }
    
    public final void status(int n) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
}