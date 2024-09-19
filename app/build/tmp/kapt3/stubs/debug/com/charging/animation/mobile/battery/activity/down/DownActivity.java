package com.charging.animation.mobile.battery.activity.down;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.Glide;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.activity.down.dialog.ProcessingDialog;
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.database.Database;
import com.charging.animation.mobile.battery.databinding.ActivityDownBinding;
import com.charging.animation.mobile.battery.dialog.DialogWatchAds;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.Constants;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.SystemUtil;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import java.io.File;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ,2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001,B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001dH\u0016J\b\u0010!\u001a\u00020\u001dH\u0016J\b\u0010\"\u001a\u00020\u001dH\u0007J\u0010\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u0003H\u0016J\u0012\u0010\'\u001a\u00020\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010(\u001a\u00020\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0010\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u000eH\u0002J\b\u0010+\u001a\u00020\u001dH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u00a8\u0006-"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/down/DownActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityDownBinding;", "Lcom/charging/animation/mobile/battery/activity/down/DownViewModel;", "()V", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "dialog", "Lcom/charging/animation/mobile/battery/activity/down/dialog/ProcessingDialog;", "getDialog", "()Lcom/charging/animation/mobile/battery/activity/down/dialog/ProcessingDialog;", "setDialog", "(Lcom/charging/animation/mobile/battery/activity/down/dialog/ProcessingDialog;)V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "path", "getPath", "setPath", "results", "Lcom/charging/animation/mobile/battery/api/Results;", "getResults", "()Lcom/charging/animation/mobile/battery/api/Results;", "setResults", "(Lcom/charging/animation/mobile/battery/api/Results;)V", "blur", "", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "init", "listener", "receiver", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setViewModel", "show", "showAds", "startDownload", "link", "viewModel", "Companion", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class DownActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityDownBinding, com.charging.animation.mobile.battery.activity.down.DownViewModel> {
    @org.jetbrains.annotations.Nullable
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable
    private java.lang.String path;
    @org.jetbrains.annotations.Nullable
    private com.charging.animation.mobile.battery.api.Results results;
    @org.jetbrains.annotations.Nullable
    private com.charging.animation.mobile.battery.activity.down.dialog.ProcessingDialog dialog;
    private android.content.BroadcastReceiver broadcastReceiver;
    @org.jetbrains.annotations.NotNull
    public static final com.charging.animation.mobile.battery.activity.down.DownActivity.Companion Companion = null;
    private static boolean DOWN = false;
    
    public DownActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPath() {
        return null;
    }
    
    public final void setPath(@org.jetbrains.annotations.Nullable
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.charging.animation.mobile.battery.api.Results getResults() {
        return null;
    }
    
    public final void setResults(@org.jetbrains.annotations.Nullable
    com.charging.animation.mobile.battery.api.Results p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.charging.animation.mobile.battery.activity.down.dialog.ProcessingDialog getDialog() {
        return null;
    }
    
    public final void setDialog(@org.jetbrains.annotations.Nullable
    com.charging.animation.mobile.battery.activity.down.dialog.ProcessingDialog p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityDownBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.down.DownViewModel setViewModel() {
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
    
    private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm() {
        return null;
    }
    
    private final void blur() {
    }
    
    @android.annotation.SuppressLint(value = {"UnspecifiedRegisterReceiverFlag"})
    public final void receiver() {
    }
    
    private final void startDownload(java.lang.String link) {
    }
    
    private final void show(com.charging.animation.mobile.battery.api.Results results) {
    }
    
    public final void showAds(@org.jetbrains.annotations.Nullable
    com.charging.animation.mobile.battery.api.Results results) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/down/DownActivity$Companion;", "", "()V", "DOWN", "", "getDOWN", "()Z", "setDOWN", "(Z)V", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean getDOWN() {
            return false;
        }
        
        public final void setDOWN(boolean p0) {
        }
    }
}