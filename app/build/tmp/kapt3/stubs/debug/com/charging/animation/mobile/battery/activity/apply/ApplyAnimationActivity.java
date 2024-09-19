package com.charging.animation.mobile.battery.activity.apply;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.activity.success.SuccessActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.databinding.ActivityApplyAnimationUpdateBinding;
import com.charging.animation.mobile.battery.databinding.LayoutWalpaperSelectOptionBinding;
import com.charging.animation.mobile.battery.dialog.DialogSetting;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.service.LiveWallpaperService;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.SharePrefUtils;
import com.charging.animation.mobile.battery.util.SystemUtil;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u00017B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0002J\u0006\u0010 \u001a\u00020\u001bJ\u001a\u0010!\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\t2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020\tH\u0002J\b\u0010(\u001a\u00020\tH\u0002J\b\u0010)\u001a\u00020\u001bH\u0016J\b\u0010*\u001a\u00020\u001bH\u0003J\b\u0010+\u001a\u00020\u001bH\u0016J\b\u0010,\u001a\u00020\u001bH\u0014J\b\u0010-\u001a\u00020\u001bH\u0014J\u0006\u0010\u000e\u001a\u00020\u001bJ\u0010\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020\u001bH\u0002J\b\u00102\u001a\u00020\u0003H\u0016J\u0012\u00103\u001a\u00020\u001b2\b\u00104\u001a\u0004\u0018\u000105H\u0002J\b\u00106\u001a\u00020\u001bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u00068"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/apply/ApplyAnimationActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityApplyAnimationUpdateBinding;", "Lcom/charging/animation/mobile/battery/activity/apply/ApplyAnimationViewModel;", "()V", "isClicked", "", "isPermission", "link", "", "getLink", "()Ljava/lang/String;", "setLink", "(Ljava/lang/String;)V", "receiver", "Landroid/content/BroadcastReceiver;", "getReceiver", "()Landroid/content/BroadcastReceiver;", "setReceiver", "(Landroid/content/BroadcastReceiver;)V", "type", "", "getType", "()I", "setType", "(I)V", "blur", "", "checkOverlayPermission", "context", "Landroid/content/Context;", "dialogForPermission", "done", "getBitmap", "path", "onClicked", "Lcom/charging/animation/mobile/battery/activity/apply/ApplyAnimationActivity$OnClickedListener;", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "getCurrentDateInCustomFormat", "getCurrentTimeInCustomFormat", "init", "isChanging", "listener", "onDestroy", "onResume", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setUpView", "setViewModel", "showDialogSetWallpaper", "wallpaper", "Landroid/graphics/Bitmap;", "viewModel", "OnClickedListener", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class ApplyAnimationActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityApplyAnimationUpdateBinding, com.charging.animation.mobile.battery.activity.apply.ApplyAnimationViewModel> {
    @org.jetbrains.annotations.Nullable
    private java.lang.String link = "";
    private int type = 0;
    private boolean isClicked = false;
    private boolean isPermission = false;
    @org.jetbrains.annotations.NotNull
    private android.content.BroadcastReceiver receiver;
    
    public ApplyAnimationActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLink() {
        return null;
    }
    
    public final void setLink(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    public final int getType() {
        return 0;
    }
    
    public final void setType(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityApplyAnimationUpdateBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.apply.ApplyAnimationViewModel setViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void init() {
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
    
    public final void receiver() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.BroadcastReceiver getReceiver() {
        return null;
    }
    
    public final void setReceiver(@org.jetbrains.annotations.NotNull
    android.content.BroadcastReceiver p0) {
    }
    
    private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm() {
        return null;
    }
    
    private final void blur() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void listener() {
    }
    
    @java.lang.Override
    public void viewModel() {
    }
    
    private final boolean checkOverlayPermission(android.content.Context context) {
        return false;
    }
    
    private final void dialogForPermission() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    private final void showDialogSetWallpaper(android.graphics.Bitmap wallpaper) {
    }
    
    public final void done() {
    }
    
    private final void getBitmap(java.lang.String path, com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity.OnClickedListener onClicked) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/apply/ApplyAnimationActivity$OnClickedListener;", "", "onClicked", "", "bitmap", "Landroid/graphics/Bitmap;", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
    public static abstract interface OnClickedListener {
        
        public abstract void onClicked(@org.jetbrains.annotations.Nullable
        android.graphics.Bitmap bitmap);
    }
}