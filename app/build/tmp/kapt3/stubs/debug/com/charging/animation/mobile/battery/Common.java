package com.charging.animation.mobile.battery;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import androidx.core.content.ContextCompat;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0006J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0004J\u0016\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0004J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0004J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\rJ\u001d\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0010J\u001d\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0010J\u0016\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0010\u00a8\u0006!"}, d2 = {"Lcom/charging/animation/mobile/battery/Common;", "", "()V", "checkMediaFilePermission", "", "context", "Landroid/content/Context;", "checkOverlayPermission", "getFirstOpen", "mContext", "getFirstUserPatternLock", "getFirstUserPinLock", "getLang", "", "getLightMode", "getOpenVersion", "", "getOverLay", "getPosition", "setFirstOpen", "", "open", "setFirstUserPatternLock", "setFirstUserPinLock", "setLang", "setLightMode", "(Landroid/content/Context;Ljava/lang/Boolean;)V", "setOpenVersion", "version", "setOverLay", "setPosition", "setPreLanguageflag", "flag", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class Common {
    @org.jetbrains.annotations.NotNull
    public static final com.charging.animation.mobile.battery.Common INSTANCE = null;
    
    private Common() {
        super();
    }
    
    public final void setLightMode(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Boolean open) {
    }
    
    public final boolean getLightMode(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public final void setOverLay(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.Boolean open) {
    }
    
    public final boolean getOverLay(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public final void setFirstOpen(@org.jetbrains.annotations.NotNull
    android.content.Context context, boolean open) {
    }
    
    public final boolean getFirstOpen(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public final void setOpenVersion(@org.jetbrains.annotations.NotNull
    android.content.Context context, int version) {
    }
    
    public final int getOpenVersion(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLang(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return null;
    }
    
    public final void setLang(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String open) {
    }
    
    public final void setPosition(@org.jetbrains.annotations.NotNull
    android.content.Context context, int open) {
    }
    
    public final int getPosition(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return 0;
    }
    
    public final void setPreLanguageflag(@org.jetbrains.annotations.NotNull
    android.content.Context context, int flag) {
    }
    
    public final void setFirstUserPinLock(@org.jetbrains.annotations.NotNull
    android.content.Context context, boolean open) {
    }
    
    public final boolean getFirstUserPinLock(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public final void setFirstUserPatternLock(@org.jetbrains.annotations.NotNull
    android.content.Context context, boolean open) {
    }
    
    public final boolean getFirstUserPatternLock(@org.jetbrains.annotations.NotNull
    android.content.Context mContext) {
        return false;
    }
    
    public final boolean checkOverlayPermission(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
    
    public final boolean checkMediaFilePermission(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
}