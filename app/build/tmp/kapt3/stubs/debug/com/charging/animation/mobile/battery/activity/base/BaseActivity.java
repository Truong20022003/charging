package com.charging.animation.mobile.battery.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.viewbinding.ViewBinding;
import com.charging.animation.mobile.battery.util.SystemUtil;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0014H&J\b\u0010\u001c\u001a\u00020\u0014H&J\u0012\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0014H\u0014J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#H\u0016J\u0015\u0010\n\u001a\u00028\u00002\u0006\u0010$\u001a\u00020\u001aH&\u00a2\u0006\u0002\u0010%J\r\u0010\u0010\u001a\u00028\u0001H&\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010&\u001a\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u001fJ\u001e\u0010)\u001a\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u001fJ\u001e\u0010*\u001a\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u001fJ\b\u0010\r\u001a\u00020\u0014H&R\u001c\u0010\u0007\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\u00028\u0001X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006+"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "T", "Landroidx/viewbinding/ViewBinding;", "V", "Landroidx/lifecycle/ViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "setBinding", "(Landroidx/viewbinding/ViewBinding;)V", "Landroidx/viewbinding/ViewBinding;", "viewModel", "getViewModel", "()Landroidx/lifecycle/ViewModel;", "setViewModel", "(Landroidx/lifecycle/ViewModel;)V", "Landroidx/lifecycle/ViewModel;", "fullScreen", "", "activity", "Landroid/app/Activity;", "getInflatedLayout", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "init", "listener", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onWindowFocusChanged", "hasFocus", "", "layoutInflater", "(Landroid/view/LayoutInflater;)Landroidx/viewbinding/ViewBinding;", "showActivity", "Ljava/lang/Class;", "bundle", "showActivityFinish", "showActivityFinishAffinity", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public abstract class BaseActivity<T extends androidx.viewbinding.ViewBinding, V extends androidx.lifecycle.ViewModel> extends androidx.appcompat.app.AppCompatActivity {
    protected T binding;
    protected V viewModel;
    
    public BaseActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    protected final T getBinding() {
        return null;
    }
    
    protected final void setBinding(@org.jetbrains.annotations.NotNull
    T p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    protected final V getViewModel() {
        return null;
    }
    
    protected final void setViewModel(@org.jetbrains.annotations.NotNull
    V p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract T setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater);
    
    @org.jetbrains.annotations.NotNull
    public abstract V setViewModel();
    
    private final android.view.View getInflatedLayout(android.view.LayoutInflater inflater) {
        return null;
    }
    
    public abstract void init();
    
    public abstract void listener();
    
    public abstract void viewModel();
    
    @java.lang.Override
    protected void onStart() {
    }
    
    @java.lang.Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }
    
    private final void fullScreen(android.app.Activity activity) {
    }
    
    public final void showActivity(@org.jetbrains.annotations.NotNull
    java.lang.Class<?> activity, @org.jetbrains.annotations.Nullable
    android.os.Bundle bundle) {
    }
    
    public final void showActivityFinishAffinity(@org.jetbrains.annotations.NotNull
    java.lang.Class<?> activity, @org.jetbrains.annotations.Nullable
    android.os.Bundle bundle) {
    }
    
    public final void showActivityFinish(@org.jetbrains.annotations.NotNull
    java.lang.Class<?> activity, @org.jetbrains.annotations.Nullable
    android.os.Bundle bundle) {
    }
}