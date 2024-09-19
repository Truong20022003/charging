package com.charging.animation.mobile.battery.activity.animation;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.databinding.ActivityAnimationBinding;
import com.charging.animation.mobile.battery.util.Data;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0003J\b\u0010\u0019\u001a\u00020\u000eH\u0016J\u0012\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u000eH\u0014J\b\u0010\u001e\u001a\u00020\u000eH\u0014J\u0006\u0010\u0007\u001a\u00020\u000eJ\b\u0010\u001f\u001a\u00020\u000eH\u0002J\u0010\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u000eH\u0002J\b\u0010$\u001a\u00020\u0003H\u0016J\b\u0010%\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\'"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/animation/AnimationActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityAnimationBinding;", "Lcom/charging/animation/mobile/battery/activity/animation/AnimationViewModel;", "()V", "doubleBackToExitPressedOnce", "", "receiver", "Landroid/content/BroadcastReceiver;", "getReceiver", "()Landroid/content/BroadcastReceiver;", "setReceiver", "(Landroid/content/BroadcastReceiver;)V", "blur", "", "close", "time", "", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "getCurrentDateInCustomFormat", "", "getCurrentTimeInCustomFormat", "init", "isChanging", "listener", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "screen", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setUpView", "setViewModel", "viewModel", "Companion", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class AnimationActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityAnimationBinding, com.charging.animation.mobile.battery.activity.animation.AnimationViewModel> {
    private boolean doubleBackToExitPressedOnce = false;
    @org.jetbrains.annotations.NotNull
    private android.content.BroadcastReceiver receiver;
    @org.jetbrains.annotations.NotNull
    public static final com.charging.animation.mobile.battery.activity.animation.AnimationActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmField
    public static java.lang.String ACTION_STOP_ANIMATION = "ACTION_STOP_ANIMATION";
    @kotlin.jvm.JvmField
    public static boolean ACTIVE = false;
    
    public AnimationActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityAnimationBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.animation.AnimationViewModel setViewModel() {
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
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void screen() {
    }
    
    public final void receiver() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.BroadcastReceiver getReceiver() {
        return null;
    }
    
    public final void setReceiver(@org.jetbrains.annotations.NotNull
    android.content.BroadcastReceiver p0) {
    }
    
    private final void close(int time) {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void isChanging() {
    }
    
    private final void setUpView() {
    }
    
    private final java.lang.String getCurrentTimeInCustomFormat() {
        return null;
    }
    
    private final java.lang.String getCurrentDateInCustomFormat() {
        return null;
    }
    
    private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm() {
        return null;
    }
    
    private final void blur() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/animation/AnimationActivity$Companion;", "", "()V", "ACTION_STOP_ANIMATION", "", "ACTIVE", "", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}