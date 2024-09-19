package com.charging.animation.mobile.battery.fragment.gallery.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.apply.ApplyAnimationActivity;
import com.charging.animation.mobile.battery.activity.base.BaseFragment;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.database.Database;
import com.charging.animation.mobile.battery.databinding.FragmentDownBinding;
import com.charging.animation.mobile.battery.fragment.gallery.GalleryViewModel;
import com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter;
import com.charging.animation.mobile.battery.listener.Listener;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0002H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001b"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/gallery/fragment/DownFragment;", "Lcom/charging/animation/mobile/battery/activity/base/BaseFragment;", "Lcom/charging/animation/mobile/battery/databinding/FragmentDownBinding;", "Lcom/charging/animation/mobile/battery/fragment/gallery/GalleryViewModel;", "()V", "adapterGallery", "Lcom/charging/animation/mobile/battery/fragment/gallery/adapter/GalleryAdapter;", "getAdapterGallery", "()Lcom/charging/animation/mobile/battery/fragment/gallery/adapter/GalleryAdapter;", "setAdapterGallery", "(Lcom/charging/animation/mobile/battery/fragment/gallery/adapter/GalleryAdapter;)V", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "listGallery", "", "Lcom/charging/animation/mobile/battery/api/Results;", "getListGallery", "()Ljava/util/List;", "setListGallery", "(Ljava/util/List;)V", "getViewBinding", "init", "", "listener", "onDestroy", "setViewModel", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class DownFragment extends com.charging.animation.mobile.battery.activity.base.BaseFragment<com.charging.animation.mobile.battery.databinding.FragmentDownBinding, com.charging.animation.mobile.battery.fragment.gallery.GalleryViewModel> {
    @org.jetbrains.annotations.Nullable
    private com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter adapterGallery;
    public java.util.List<com.charging.animation.mobile.battery.api.Results> listGallery;
    private android.content.BroadcastReceiver broadcastReceiver;
    
    public DownFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter getAdapterGallery() {
        return null;
    }
    
    public final void setAdapterGallery(@org.jetbrains.annotations.Nullable
    com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListGallery() {
        return null;
    }
    
    public final void setListGallery(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.FragmentDownBinding getViewBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.fragment.gallery.GalleryViewModel setViewModel() {
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
    public void onDestroy() {
    }
}