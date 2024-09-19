package com.charging.animation.mobile.battery.fragment.setting;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseFragment;
import com.charging.animation.mobile.battery.activity.language.LanguageSettingActivity;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.activity.policy.PolicyActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.databinding.FragmentSettingUpdateBinding;
import com.charging.animation.mobile.battery.dialog.RatingDialog;
import com.charging.animation.mobile.battery.dialog.RatingDialog.OnPress;
import com.charging.animation.mobile.battery.fragment.alarm.AlarmFragment;
import com.charging.animation.mobile.battery.service.BatteryService;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.SharePrefUtils;
import com.charging.animation.mobile.battery.util.Util;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManagerFactory;
import java.util.Locale;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0006\u0010\u0011\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\"\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0017J\b\u0010\u001a\u001a\u00020\u000fH\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0016J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\u0006\u0010\u001f\u001a\u00020\u000fJ\b\u0010 \u001a\u00020\u000fH\u0002J\b\u0010!\u001a\u00020\u000fH\u0002J\b\u0010\"\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/setting/SettingFragment;", "Lcom/charging/animation/mobile/battery/activity/base/BaseFragment;", "Lcom/charging/animation/mobile/battery/databinding/FragmentSettingUpdateBinding;", "Lcom/charging/animation/mobile/battery/fragment/setting/SettingViewModel;", "()V", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "intent", "Landroid/content/Intent;", "isLive", "", "isPercent", "isService", "isShare", "alarm", "", "clearFragmentContainer", "feedback", "getViewBinding", "init", "listener", "onActivityResult", "requestCode", "", "resultCode", "data", "onDestroy", "onResume", "permissionLive", "permissionService", "setViewModel", "setting", "share", "showRateDialog", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class SettingFragment extends com.charging.animation.mobile.battery.activity.base.BaseFragment<com.charging.animation.mobile.battery.databinding.FragmentSettingUpdateBinding, com.charging.animation.mobile.battery.fragment.setting.SettingViewModel> {
    private boolean isService = false;
    private boolean isPercent = false;
    private boolean isLive = false;
    private android.content.Intent intent;
    private boolean isShare = false;
    private android.content.BroadcastReceiver broadcastReceiver;
    
    public SettingFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.FragmentSettingUpdateBinding getViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.fragment.setting.SettingViewModel setViewModel() {
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
    
    private final void permissionLive() {
    }
    
    @androidx.annotation.RequiresApi(api = android.os.Build.VERSION_CODES.M)
    @java.lang.Override
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final void permissionService() {
    }
    
    private final void showRateDialog() {
    }
    
    private final void share() {
    }
    
    public final void feedback() {
    }
    
    private final void clearFragmentContainer() {
    }
    
    public final void alarm() {
    }
    
    public final void setting() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
}