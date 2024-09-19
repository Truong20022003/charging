package com.charging.animation.mobile.battery.activity.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.activity.info.InfoActivity;
import com.charging.animation.mobile.battery.activity.main.adapter.BottomAdapter;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.database.Database;
import com.charging.animation.mobile.battery.databinding.ActivityMainUpdateBinding;
import com.charging.animation.mobile.battery.dialog.ExitDialog;
import com.charging.animation.mobile.battery.dialog.RatingDialog;
import com.charging.animation.mobile.battery.dialog.RatingDialog.OnPress;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.RealPathUtil;
import com.charging.animation.mobile.battery.util.SharePrefUtils;
import com.charging.animation.mobile.battery.util.SystemUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManagerFactory;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0007J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0006\u0010\u0018\u001a\u00020\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\"\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0015J\b\u0010$\u001a\u00020\u0014H\u0017J\b\u0010%\u001a\u00020\u0014H\u0014J\u0006\u0010&\u001a\u00020\nJ\b\u0010\'\u001a\u00020\u0014H\u0002J\u0006\u0010(\u001a\u00020\u0014J\u0010\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u0014H\u0002J\b\u0010-\u001a\u00020\u0003H\u0016J\b\u0010.\u001a\u00020\u0014H\u0002J\u000e\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020\nJ\u0006\u00101\u001a\u00020\u0014J\u0006\u00102\u001a\u00020\u0014J\b\u00103\u001a\u00020\u0014H\u0002J\b\u00104\u001a\u00020\u0014H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/main/MainActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityMainUpdateBinding;", "Lcom/charging/animation/mobile/battery/activity/main/MainViewModel;", "()V", "alertDialog", "Landroidx/appcompat/app/AlertDialog;", "bottomAdapter", "Lcom/charging/animation/mobile/battery/activity/main/adapter/BottomAdapter;", "checkAlarm", "", "checkGallery", "checkPer", "count", "", "permissionsResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "blur", "", "changeMenu", "dialogExit", "dialogSetting", "fragment", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "home", "init", "listener", "loadFragment", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onResume", "permission", "requestStorage", "select", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setUpUi", "setViewModel", "showDialogBatteryNotification", "showRateDialog", "exit", "startAlarm", "startSetting", "textGradient", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class MainActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityMainUpdateBinding, com.charging.animation.mobile.battery.activity.main.MainViewModel> {
    private com.charging.animation.mobile.battery.activity.main.adapter.BottomAdapter bottomAdapter;
    private boolean checkGallery = false;
    private boolean checkAlarm = false;
    private int count = 0;
    private androidx.appcompat.app.AlertDialog alertDialog;
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> permissionsResult = null;
    private boolean checkPer = false;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityMainUpdateBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.main.MainViewModel setViewModel() {
        return null;
    }
    
    @java.lang.Override
    public void init() {
    }
    
    private final void dialogSetting() {
    }
    
    @java.lang.Override
    public void listener() {
    }
    
    @java.lang.Override
    public void viewModel() {
    }
    
    private final void textGradient() {
    }
    
    private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm() {
        return null;
    }
    
    private final void blur() {
    }
    
    private final void loadFragment() {
    }
    
    public final void fragment() {
    }
    
    public final void home() {
    }
    
    @android.annotation.SuppressLint(value = {"ResourceAsColor"})
    public final void changeMenu() {
    }
    
    public final void select() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    private final void setUpUi() {
    }
    
    private final void dialogExit() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingSuperCall"})
    @java.lang.Override
    public void onBackPressed() {
    }
    
    private final void requestStorage() {
    }
    
    public final boolean permission() {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    @java.lang.Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    public final void showRateDialog(boolean exit) {
    }
    
    private final void showDialogBatteryNotification() {
    }
    
    public final void startAlarm() {
    }
    
    public final void startSetting() {
    }
}