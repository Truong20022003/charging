package com.charging.animation.mobile.battery.util;

import android.content.Context;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u001fJ\u001e\u0010$\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0006J\u001e\u0010%\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0014J\u001e\u0010&\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004J\u001e\u0010\'\u001a\u00020(2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u001fJ\u001e\u0010)\u001a\u00020(2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0006J\u001e\u0010*\u001a\u00020(2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0014J\u001e\u0010+\u001a\u00020(2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/charging/animation/mobile/battery/util/SharedPreferencesRepository;", "", "()V", "ARG_INTENT_RESULT", "", "CODE_RESULT", "", "DATA_PRESS_HOLD", "INTENT_RESULT", "INTRO", "LANGUAGE", "PREF_NAME", "PRESS_HOLD_HOUR", "PRESS_HOLD_HOUR_DEFAULT", "PRESS_HOLD_MINUTE", "PRESS_HOLD_MINUTE_DEFAULT", "PRESS_HOLD_MODE_CLICK_INTERVAL_MODE", "PRESS_HOLD_MODE_CLICK_INTERVAL_MODE_DEFAULT", "PRESS_HOLD_MODE_CLICK_INTERVAL_TIME", "PRESS_HOLD_MODE_CLICK_INTERVAL_TIME_DEFAULT", "", "PRESS_HOLD_MODE_PRESS_DURATION", "PRESS_HOLD_MODE_PRESS_DURATION_DEFAULT", "PRESS_HOLD_MODE_STOP_AFTER", "PRESS_HOLD_MODE_STOP_AFTER_DEFAULT", "PRESS_HOLD_SECOND", "PRESS_HOLD_SECOND_DEFAULT", "PRESS_HOLD_SUM_LOOP", "PRESS_HOLD_SUM_LOOP_DEFAULT", "RATE", "getBoolean", "", "context", "Landroid/content/Context;", "key", "value", "getInt", "getLong", "getString", "setBoolean", "", "setInt", "setLong", "setString", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class SharedPreferencesRepository {
    @org.jetbrains.annotations.NotNull
    public static final com.charging.animation.mobile.battery.util.SharedPreferencesRepository INSTANCE = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_NAME = "SharedPreferencesRepository";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String INTRO = "intro";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String LANGUAGE = "language";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String RATE = "rate";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_MODE_STOP_AFTER = "PRESS_HOLD_MODE_STOP_AFTER";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_MODE_PRESS_DURATION = "PRESS_HOLD_MODE_PRESS_DURATION";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_MODE_CLICK_INTERVAL_TIME = "PRESS_HOLD_MODE_CLICK_INTERVAL_TIME";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_MODE_CLICK_INTERVAL_MODE = "PRESS_HOLD_MODE_CLICK_INTERVAL_MODE";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_HOUR = "PRESS_HOLD_HOUR";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_MINUTE = "PRESS_HOLD_MINUTE";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_SECOND = "PRESS_HOLD_SECOND";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PRESS_HOLD_SUM_LOOP = "PRESS_HOLD_SUM_LOOP";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DATA_PRESS_HOLD = "DataPressHold1.json";
    public static final long PRESS_HOLD_MODE_PRESS_DURATION_DEFAULT = 10000L;
    public static final long PRESS_HOLD_MODE_CLICK_INTERVAL_TIME_DEFAULT = 1000L;
    public static final int PRESS_HOLD_MODE_STOP_AFTER_DEFAULT = 0;
    public static final int PRESS_HOLD_SUM_LOOP_DEFAULT = 10;
    public static final int PRESS_HOLD_MODE_CLICK_INTERVAL_MODE_DEFAULT = 0;
    public static final int PRESS_HOLD_HOUR_DEFAULT = 0;
    public static final int PRESS_HOLD_MINUTE_DEFAULT = 1;
    public static final int PRESS_HOLD_SECOND_DEFAULT = 0;
    public static final int CODE_RESULT = 1000;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ARG_INTENT_RESULT = "intent_result";
    public static final int INTENT_RESULT = 1;
    
    private SharedPreferencesRepository() {
        super();
    }
    
    public final void setString(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    public final void setBoolean(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, boolean value) {
    }
    
    public final int getInt(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, int value) {
        return 0;
    }
    
    public final void setInt(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, int value) {
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, boolean value) {
        return false;
    }
    
    public final long getLong(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, long value) {
        return 0L;
    }
    
    public final void setLong(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String key, long value) {
    }
}