package com.charging.animation.mobile.battery.activity.language;

import android.view.LayoutInflater;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseActivity;
import com.charging.animation.mobile.battery.activity.language.model.LanguageModel;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.databinding.ActivityLanguageSettingBinding;
import com.charging.animation.mobile.battery.util.SharedPreferencesRepository;
import com.charging.animation.mobile.battery.util.SystemUtil;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/language/LanguageSettingActivity;", "Lcom/charging/animation/mobile/battery/activity/base/BaseActivity;", "Lcom/charging/animation/mobile/battery/databinding/ActivityLanguageSettingBinding;", "Lcom/charging/animation/mobile/battery/activity/language/LanguageViewModel;", "()V", "codeLang", "", "getCodeLang", "()Ljava/lang/String;", "setCodeLang", "(Ljava/lang/String;)V", "langDevice", "getLangDevice", "setLangDevice", "languageAdapter", "Lcom/charging/animation/mobile/battery/activity/language/LanguageStartAdapter;", "init", "", "listener", "setBinding", "layoutInflater", "Landroid/view/LayoutInflater;", "setViewModel", "viewModel", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class LanguageSettingActivity extends com.charging.animation.mobile.battery.activity.base.BaseActivity<com.charging.animation.mobile.battery.databinding.ActivityLanguageSettingBinding, com.charging.animation.mobile.battery.activity.language.LanguageViewModel> {
    private com.charging.animation.mobile.battery.activity.language.LanguageStartAdapter languageAdapter;
    @org.jetbrains.annotations.NotNull
    private java.lang.String langDevice = "en";
    @org.jetbrains.annotations.NotNull
    private java.lang.String codeLang = "en";
    
    public LanguageSettingActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLangDevice() {
        return null;
    }
    
    public final void setLangDevice(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCodeLang() {
        return null;
    }
    
    public final void setCodeLang(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.databinding.ActivityLanguageSettingBinding setBinding(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater layoutInflater) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.activity.language.LanguageViewModel setViewModel() {
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
}