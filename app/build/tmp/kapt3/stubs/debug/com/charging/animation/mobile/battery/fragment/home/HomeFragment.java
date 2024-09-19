package com.charging.animation.mobile.battery.fragment.home;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity;
import com.charging.animation.mobile.battery.activity.base.BaseFragment;
import com.charging.animation.mobile.battery.activity.down.DownActivity;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.databinding.FragmentHomeUpdateBinding;
import com.charging.animation.mobile.battery.fragment.home.adapter.AnimContentAndAdsAdapter;
import com.charging.animation.mobile.battery.fragment.home.adapter.CategoryAdapter;
import com.charging.animation.mobile.battery.fragment.home.model.CategoryModel;
import com.charging.animation.mobile.battery.fragment.home.repository.HomeRepository;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.Constants;
import kotlinx.coroutines.Dispatchers;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\r\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\u0006\u0010\u0017\u001a\u00020\rJ\b\u0010\u0018\u001a\u00020\u0002H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\rJ\b\u0010\u001b\u001a\u00020\u0014H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0007J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002J\u0006\u0010 \u001a\u00020\u0014J\b\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020\u0014H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/home/HomeFragment;", "Lcom/charging/animation/mobile/battery/activity/base/BaseFragment;", "Lcom/charging/animation/mobile/battery/databinding/FragmentHomeUpdateBinding;", "Lcom/charging/animation/mobile/battery/fragment/home/HomeViewModel;", "()V", "adapter", "Lcom/charging/animation/mobile/battery/fragment/home/adapter/AnimContentAndAdsAdapter;", "adapterCategory", "Lcom/charging/animation/mobile/battery/fragment/home/adapter/CategoryAdapter;", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "list", "", "Lcom/charging/animation/mobile/battery/api/Results;", "nameCategory", "", "resultReload", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "check", "", "listAll", "", "results", "getViewBinding", "init", "intent", "listener", "loadData", "onDestroy", "recycleViewAnimation", "recycleViewCategory", "reset", "setViewModel", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class HomeFragment extends com.charging.animation.mobile.battery.activity.base.BaseFragment<com.charging.animation.mobile.battery.databinding.FragmentHomeUpdateBinding, com.charging.animation.mobile.battery.fragment.home.HomeViewModel> {
    private java.util.List<com.charging.animation.mobile.battery.api.Results> list;
    private com.charging.animation.mobile.battery.fragment.home.adapter.AnimContentAndAdsAdapter adapter;
    private com.charging.animation.mobile.battery.fragment.home.adapter.CategoryAdapter adapterCategory;
    private java.lang.String nameCategory = "";
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultReload;
    private android.content.BroadcastReceiver broadcastReceiver;
    
    public HomeFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.FragmentHomeUpdateBinding getViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.fragment.home.HomeViewModel setViewModel() {
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
    
    private final void recycleViewCategory() {
    }
    
    private final void recycleViewAnimation() {
    }
    
    public final void reset() {
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    public final void loadData() {
    }
    
    public final void intent(@org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.api.Results results) {
    }
    
    public final void check(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.charging.animation.mobile.battery.api.Results> listAll, @org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.api.Results results) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
}