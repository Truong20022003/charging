package com.charging.animation.mobile.battery.activity.permission;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.bumptech.glide.Glide;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.databinding.ActivityPermnissionBinding;
import com.charging.animation.mobile.battery.util.SystemUtil;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import java.util.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0014J\b\u0010 \u001a\u00020\u0017H\u0002J\u0010\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0003H\u0016J\b\u0010%\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00108\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00108\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00108\u0002X\u0083D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/permission/PermissionActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityPermnissionBinding;", "Lcom/charging/animation/mobile/battery/activity/permission/PermissionViewModel;", "()V", "checkStorage", "", "dialog", "Landroid/app/Dialog;", "getDialog", "()Landroid/app/Dialog;", "setDialog", "(Landroid/app/Dialog;)V", "permissionsResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "readMediaPermission", "readMediaPermission2", "readMediaPermission3", "readMediaPermission4", "readMediaPermission5", "blur", "", "checkAndRequestPermissions", "dialogForPermission", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "init", "listener", "nextActivity", "onResume", "requestStorage", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setViewModel", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class PermissionActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityPermnissionBinding, com.charging.animation.mobile.battery.activity.permission.PermissionViewModel> {
    private boolean checkStorage = false;
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.TIRAMISU)
    private final java.lang.String readMediaPermission = "android.permission.READ_MEDIA_VIDEO";
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.TIRAMISU)
    private final java.lang.String readMediaPermission2 = "android.permission.READ_MEDIA_IMAGES";
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.TIRAMISU)
    private final java.lang.String readMediaPermission3 = "android.permission.READ_MEDIA_AUDIO";
    private final java.lang.String readMediaPermission4 = "android.permission.READ_EXTERNAL_STORAGE";
    private final java.lang.String readMediaPermission5 = "android.permission.WRITE_EXTERNAL_STORAGE";
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> permissionsResult = null;
    @org.jetbrains.annotations.Nullable
    private android.app.Dialog dialog;
    
    public PermissionActivity() {
        super();
    }
    
    private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm() {
        return null;
    }
    
    private final void blur() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityPermnissionBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.permission.PermissionViewModel setViewModel() {
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
    
    private final void requestStorage() {
    }
    
    private final void nextActivity() {
    }
    
    private final void checkAndRequestPermissions() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.app.Dialog getDialog() {
        return null;
    }
    
    public final void setDialog(@org.jetbrains.annotations.Nullable
    android.app.Dialog p0) {
    }
    
    private final void dialogForPermission() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
}