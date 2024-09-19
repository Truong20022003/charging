package com.charging.animation.mobile.battery.util;

import android.content.Context;

public class Data {
    public static int TYPE = 0;
    public static String PATH = "";

    public static String CONFIG = "CONFIG";
    public static String SERVICE_BATTER = "SERVICE_BATTER";
    public static String SELECT_RINGTONE = "SELECT_RINGTONE";
    public static String SELECT_RINGTONE_NAME = "SELECT_RINGTONE_NAME";
    public static String SERVICE_BATTER_FULL = "SERVICE_BATTER_FULL";
    public static String BATTERY_PERCENT = "BATTERY_PERCENT";
    public static String SERVICE_LIVE = "SERVICE_LIVE";
    public static String SELECT_WALLPAPER_PATH = "SELECT_WALLPAPER_PATH";
    public static String TUTORIAL = "TUTORIAL";
    public static String LANGUAGE = "LANGUAGE";
    public static String SETTING_TIME = "SETTING_TIME";
    public static String SETTING_OFF = "SETTING_OFF";
    public static String DELETE_ALL_DATA = "DELETE_ALL_DATA";
    public static String TYPE_ANIM = "TYPE_ANIM";
    public static String PATH_ANIM = "PATH_ANIM";

    public static String ADS = "ADS";

    public static String LOW = "LOW";
    public static String HIGH = "HIGH";


    public static void setADS(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(ADS,select)
                .apply();

    }

    public static boolean getADS(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(ADS,false);
    }


    public static void setDeleteAllData(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(DELETE_ALL_DATA,select)
                .apply();

    }

    public static boolean getDeleteAllData(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(DELETE_ALL_DATA,false);
    }

    public static void setPathAnim(Context context, String select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putString(PATH_ANIM,select)
                .apply();

    }

    public static String getPathAnim(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getString(PATH_ANIM,"file:///android_asset/Animal/animal1.gif");
    }

    public static void setTypeAnim(Context context, int select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putInt(TYPE_ANIM,select)
                .apply();
    }

    public static int getTypeAnim(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getInt(TYPE_ANIM,0);
    }

    public static void setSettingTime(Context context, int select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putInt(SETTING_TIME,select)
                .apply();

    }

    public static int getSettingTime(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getInt(SETTING_TIME,0);
    }

    public static void setSettingOff(Context context, int select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putInt(SETTING_OFF,select)
                .apply();

    }

    public static int getSettingOff(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getInt(SETTING_OFF,0);
    }




    public static void setLanguage(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(LANGUAGE,select)
                .apply();

    }

    public static boolean getLanguage(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(LANGUAGE,false);
    }


    public static void setTutorial(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(TUTORIAL,select)
                .apply();

    }

    public static boolean getTutorial(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(TUTORIAL,false);
    }


    public static void setServiceBatter(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(SERVICE_BATTER,select)
                .apply();

    }

    public static boolean getServiceBatter(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(SERVICE_BATTER,true);
    }

    public static void setServiceBatterFull(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(SERVICE_BATTER_FULL,select)
                .apply();

    }

    public static boolean getServiceBatterFull(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(SERVICE_BATTER_FULL,true);
    }

    public static void setSelectRingtone(Context context, String select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putString(SELECT_RINGTONE,select)
                .apply();

    }

    public static String getSelectRingtone(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getString(SELECT_RINGTONE,null);
    }

    public static void setSelectRingtoneName(Context context, String select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putString(SELECT_RINGTONE_NAME,select)
                .apply();

    }

    public static String getSelectRingtoneName(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getString(SELECT_RINGTONE_NAME,null);
    }

    public static void setBatteryPercent(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(BATTERY_PERCENT,select)
                .apply();

    }

    public static boolean getBatteryPercent(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(BATTERY_PERCENT,true);
    }

    public static void setServiceLive(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(SERVICE_LIVE,select)
                .apply();

    }

    public static boolean getServiceLive(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(SERVICE_LIVE,true);
    }

    public static void setSelectWallpaperPath(Context context, String select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putString(SELECT_WALLPAPER_PATH,select)
                .apply();

    }

    public static String getSelectWallpaperPath(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getString(SELECT_WALLPAPER_PATH,null);
    }

    public static void setLow(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(LOW,select)
                .apply();

    }

    public static boolean getLow(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(LOW,true);
    }


    public static void setHigh(Context context, boolean select){
        context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .edit().putBoolean(HIGH,select)
                .apply();

    }

    public static boolean getHigh(Context context){
        return context.getSharedPreferences(CONFIG,Context.MODE_PRIVATE)
                .getBoolean(HIGH,true);
    }
}
