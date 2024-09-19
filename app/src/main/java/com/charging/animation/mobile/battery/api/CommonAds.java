package com.charging.animation.mobile.battery.api;

import android.content.Context;
import android.os.Bundle;




public class CommonAds {
    public static boolean open_splash = true;

    public static boolean inter_splash = true;
    public static boolean appopen_resume = true;
    public static boolean native_language = true;
    public static boolean native_intro = true;
    public static boolean native_permission = true;
    public static boolean native_preview = true;
    public static boolean native_download = true;

    public static boolean native_apply = true;

    public static boolean native_ringtone = true;
    public static boolean native_gallery = true;
    public static boolean native_success = true;
    public static boolean Collapse_banner = true;

    public static boolean banner_all = true;
    public static boolean inter_intro = true;
    public static boolean inter_all = true;
    public static boolean inter_info = true;

    public static boolean rewarded_animation = true;

    public static long  interval_between_interstitial = 20;
    public static long  interval_interstitial_from_start = 15;

    public static String rate_aoa_inter_splash = "30_70";


    public static void logEvent(Context context, String nameEvent) {
        Bundle bundle = new Bundle();
        //FirebaseAnalytics.getInstance(context).logEvent(nameEvent, bundle);
    }

    public static void logEvent(Context context, String nameEvent, String param, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(param, value);
        //FirebaseAnalytics.getInstance(context).logEvent(nameEvent, bundle);
    }

    public static void logEvent(Context context, String nameEvent, String param, Long value) {
        Bundle bundle = new Bundle();
        bundle.putLong(param, value);
        //FirebaseAnalytics.getInstance(context).logEvent(nameEvent, bundle);
    }
}

