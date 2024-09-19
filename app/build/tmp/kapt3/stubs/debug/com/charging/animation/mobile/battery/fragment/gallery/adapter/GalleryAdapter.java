package com.charging.animation.mobile.battery.fragment.gallery.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.listener.Listener;
import eightbitlab.com.blurview.BlurAlgorithm;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import pl.droidsonroids.gif.GifImageView;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001!B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\b\b\u0001\u0010\u001c\u001a\u00020\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0018H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/gallery/adapter/GalleryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/charging/animation/mobile/battery/fragment/gallery/adapter/GalleryAdapter$MobileViewHolder;", "list", "", "Lcom/charging/animation/mobile/battery/api/Results;", "context", "Landroid/content/Context;", "listener", "Lcom/charging/animation/mobile/battery/listener/Listener;", "(Ljava/util/List;Landroid/content/Context;Lcom/charging/animation/mobile/battery/listener/Listener;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getListener", "()Lcom/charging/animation/mobile/battery/listener/Listener;", "setListener", "(Lcom/charging/animation/mobile/battery/listener/Listener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MobileViewHolder", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class GalleryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter.MobileViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.charging.animation.mobile.battery.api.Results> list;
    @org.jetbrains.annotations.NotNull
    private android.content.Context context;
    @org.jetbrains.annotations.NotNull
    private com.charging.animation.mobile.battery.listener.Listener listener;
    
    public GalleryAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> list, @org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.listener.Listener listener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.charging.animation.mobile.battery.listener.Listener getListener() {
        return null;
    }
    
    public final void setListener(@org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.listener.Listener p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter.MobileViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.fragment.gallery.adapter.GalleryAdapter.MobileViewHolder holder, @android.annotation.SuppressLint(value = {"RecyclerView"})
    int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0011\u0010\nR\u001a\u0010\u0012\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/gallery/adapter/GalleryAdapter$MobileViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "ads", "Landroid/widget/ImageView;", "getAds", "()Landroid/widget/ImageView;", "setAds", "(Landroid/widget/ImageView;)V", "blur", "Leightbitlab/com/blurview/BlurView;", "getBlur", "()Leightbitlab/com/blurview/BlurView;", "down", "getDown", "setDown", "gif", "getGif", "setGif", "", "context", "Landroid/content/Context;", "getBlurAlgorithm", "Leightbitlab/com/blurview/BlurAlgorithm;", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
    public static final class MobileViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private android.widget.ImageView gif;
        @org.jetbrains.annotations.NotNull
        private android.widget.ImageView ads;
        @org.jetbrains.annotations.NotNull
        private android.widget.ImageView down;
        @org.jetbrains.annotations.NotNull
        private final eightbitlab.com.blurview.BlurView blur = null;
        
        public MobileViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getGif() {
            return null;
        }
        
        public final void setGif(@org.jetbrains.annotations.NotNull
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getAds() {
            return null;
        }
        
        public final void setAds(@org.jetbrains.annotations.NotNull
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getDown() {
            return null;
        }
        
        public final void setDown(@org.jetbrains.annotations.NotNull
        android.widget.ImageView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final eightbitlab.com.blurview.BlurView getBlur() {
            return null;
        }
        
        private final eightbitlab.com.blurview.BlurAlgorithm getBlurAlgorithm(android.content.Context context) {
            return null;
        }
        
        public final void blur(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
    }
}