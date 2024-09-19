package com.charging.animation.mobile.battery.activity.tutorial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.activity.permission.PermissionActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.databinding.ActivityTutorialUpdateBinding;
import com.charging.animation.mobile.battery.model.TutorialModel;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.SharePrefUtils;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0015H\u0016J\b\u0010\u001d\u001a\u00020\u0015H\u0016J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0003H\u0016J\b\u0010#\u001a\u00020\u0015H\u0002J\b\u0010$\u001a\u00020\u0015H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006%"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/tutorial/TutorialActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityTutorialUpdateBinding;", "Lcom/charging/animation/mobile/battery/activity/tutorial/TutorialViewModel;", "()V", "adapter", "Lcom/charging/animation/mobile/battery/activity/tutorial/TutorialAdapter;", "getAdapter", "()Lcom/charging/animation/mobile/battery/activity/tutorial/TutorialAdapter;", "setAdapter", "(Lcom/charging/animation/mobile/battery/activity/tutorial/TutorialAdapter;)V", "check", "", "list", "", "Lcom/charging/animation/mobile/battery/model/TutorialModel;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "blur", "", "checkLive", "checkService", "dotSelect", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "init", "listener", "onBackPressed", "recycleView", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setViewModel", "start", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class TutorialActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityTutorialUpdateBinding, com.charging.animation.mobile.battery.activity.tutorial.TutorialViewModel> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.charging.animation.mobile.battery.model.TutorialModel> list;
    @org.jetbrains.annotations.Nullable
    private com.charging.animation.mobile.battery.activity.tutorial.TutorialAdapter adapter;
    private int check = 0;
    
    public TutorialActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.model.TutorialModel> getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.model.TutorialModel> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.charging.animation.mobile.battery.activity.tutorial.TutorialAdapter getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.Nullable
    com.charging.animation.mobile.battery.activity.tutorial.TutorialAdapter p0) {
    }
    
    private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm() {
        return null;
    }
    
    private final void blur() {
    }
    
    private final void recycleView() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityTutorialUpdateBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.tutorial.TutorialViewModel setViewModel() {
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
    
    private final void dotSelect() {
    }
    
    private final void start() {
    }
    
    private final void checkLive() {
    }
    
    private final void checkService() {
    }
    
    @java.lang.Override
    public void onBackPressed() {
    }
}