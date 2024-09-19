package com.charging.animation.mobile.battery.activity.ring;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.databinding.ActivityRingtoneUpdateBinding;
import com.charging.animation.mobile.battery.fragment.alarm.RingtoneAdapter;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.model.Ringtone;
import com.charging.animation.mobile.battery.util.Data;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010!\u001a\u00020\u001eH\u0016J\b\u0010\"\u001a\u00020\u001eH\u0016J\b\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001eH\u0017J\b\u0010%\u001a\u00020\u001eH\u0014J\b\u0010&\u001a\u00020\u001eH\u0014J\b\u0010\'\u001a\u00020\u001eH\u0014J\b\u0010(\u001a\u00020\u001eH\u0002J\u0010\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u0003H\u0016J\b\u0010-\u001a\u00020\u001eH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/charging/animation/mobile/battery/activity/ring/RingtoneActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityRingtoneUpdateBinding;", "Lcom/charging/animation/mobile/battery/activity/ring/RingtoneViewModel;", "()V", "adapter", "Lcom/charging/animation/mobile/battery/fragment/alarm/RingtoneAdapter;", "alertDialog", "Landroidx/appcompat/app/AlertDialog;", "count", "", "isPermission", "", "list", "", "Lcom/charging/animation/mobile/battery/model/Ringtone;", "mediaPlayer", "Landroid/media/MediaPlayer;", "notifications", "getNotifications", "()Ljava/util/List;", "permissionsResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "ringtone", "formatDuration", "durationInMillis", "", "getData", "", "getSongDuration", "songUri", "init", "listener", "loadUi", "onBackPressed", "onDestroy", "onResume", "onStop", "requestStorage", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setViewModel", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class RingtoneActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityRingtoneUpdateBinding, com.charging.animation.mobile.battery.activity.ring.RingtoneViewModel> {
    private com.charging.animation.mobile.battery.fragment.alarm.RingtoneAdapter adapter;
    private java.util.List<? extends com.charging.animation.mobile.battery.model.Ringtone> list;
    private com.charging.animation.mobile.battery.model.Ringtone ringtone;
    private android.media.MediaPlayer mediaPlayer;
    private androidx.appcompat.app.AlertDialog alertDialog;
    private boolean isPermission = false;
    private int count = 0;
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> permissionsResult = null;
    
    public RingtoneActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityRingtoneUpdateBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.ring.RingtoneViewModel setViewModel() {
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
    
    private final void getData() {
    }
    
    @android.annotation.SuppressLint(value = {"MissingSuperCall"})
    @java.lang.Override
    public void onBackPressed() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onStop() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    private final java.util.List<com.charging.animation.mobile.battery.model.Ringtone> getNotifications() {
        return null;
    }
    
    private final long getSongDuration(java.lang.String songUri) {
        return 0L;
    }
    
    private final java.lang.String formatDuration(long durationInMillis) {
        return null;
    }
    
    private final void loadUi() {
    }
    
    private final void requestStorage() {
    }
}