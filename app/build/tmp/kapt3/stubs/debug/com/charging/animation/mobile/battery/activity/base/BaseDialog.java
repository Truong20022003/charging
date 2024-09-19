package com.charging.animation.mobile.battery.activity.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.viewbinding.ViewBinding;
import com.charging.animation.mobile.battery.R;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H&J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0015\u0010\n\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u0010H&\u00a2\u0006\u0002\u0010\u0017R\u001c\u0010\u0007\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/base/BaseDialog;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "setBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "getInflatedLayout", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "layoutInflater", "(Landroid/view/LayoutInflater;)Landroidx/viewbinding/ViewBinding;", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public abstract class BaseDialog<T extends androidx.viewbinding.ViewBinding> extends android.app.Dialog {
    protected T binding;
    
    public BaseDialog(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    protected final T getBinding() {
        return null;
    }
    
    protected final void setBinding(@org.jetbrains.annotations.NotNull
    T p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract T setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater);
    
    private final android.view.View getInflatedLayout(android.view.LayoutInflater inflater) {
        return null;
    }
    
    public abstract void initView();
}