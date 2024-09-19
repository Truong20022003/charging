package com.charging.animation.mobile.battery.activity.language;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.base.BaseViewModel;
import com.charging.animation.mobile.battery.activity.language.model.LanguageModel;
import com.charging.animation.mobile.battery.util.SharedPreferencesRepository;
import com.charging.animation.mobile.battery.util.SystemUtil;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u000fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\nR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011\u00a8\u0006\u001a"}, d2 = {"Lcom/charging/animation/mobile/battery/activity/language/LanguageViewModel;", "Lcom/charging/animation/mobile/battery/activity/base/BaseViewModel;", "()V", "_codeLang", "Landroidx/lifecycle/MutableLiveData;", "", "_langDevice", "codeLang", "Landroidx/lifecycle/LiveData;", "getCodeLang", "()Landroidx/lifecycle/LiveData;", "langDevice", "getLangDevice", "languages", "", "Lcom/charging/animation/mobile/battery/activity/language/model/LanguageModel;", "getLanguages", "()Landroidx/lifecycle/MutableLiveData;", "selectedLanguage", "getSelectedLanguage", "first", "", "context", "Landroid/content/Context;", "setSelectedLanguage", "language", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class LanguageViewModel extends com.charging.animation.mobile.battery.activity.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.charging.animation.mobile.battery.activity.language.model.LanguageModel>> languages = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.charging.animation.mobile.battery.activity.language.model.LanguageModel> selectedLanguage = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _langDevice = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _codeLang = null;
    
    public LanguageViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.charging.animation.mobile.battery.activity.language.model.LanguageModel>> getLanguages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.charging.animation.mobile.battery.activity.language.model.LanguageModel> getSelectedLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getLangDevice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getCodeLang() {
        return null;
    }
    
    public final void first(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final void setSelectedLanguage(@org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.activity.language.model.LanguageModel language) {
    }
}