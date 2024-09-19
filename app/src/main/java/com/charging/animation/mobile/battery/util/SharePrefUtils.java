package com.charging.animation.mobile.battery.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePrefUtils {
    public static String email = "thuyet1989jp@gmail.com";
    public static String email1 = "thuyet1989jp@gmail.com";
    public static String subject = "";
    public static String privacy = "";
    private static SharedPreferences mSharePref;

    public static void language2(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putBoolean("open2", true);
        editor.apply();
    }

    public static boolean language(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return pre.getBoolean("open2", false);
    }

    public static  boolean checkRate = false;

    public static void init(Context context) {
        if (mSharePref == null) {
            mSharePref = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static boolean isRated(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return pre.getBoolean("rated", false);
    }

    public static int getCountOpenApp(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return pre.getInt("counts", 1);
    }

    public static void increaseCountOpenApp(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt("counts", pre.getInt("counts", 1) + 1);
        editor.apply();
    }

    public static void forceRated(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putBoolean("rated", true);
        editor.apply();
    }
    public static int getCountOpenApp2(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return pre.getInt("counts2", 0);
    }

    public static void increaseCountOpenApp2(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt("counts2", pre.getInt("counts2", 0) + 1);
        editor.apply();
    }
    public static int inter_intro(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return pre.getInt("inter_intro2", 1);
    }

    public static void inter_intro2(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt("inter_intro2", pre.getInt("inter_intro2", 1) + 1);
        editor.apply();
    }

    public static void increaseCountOpenApp3(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt("counts2", 0);
        editor.apply();
    }

    public static void setSampleInt(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        int currentValue = pre.getInt("KEY_SAMPLE_INT", 0);
        currentValue++;  // Tăng giá trị lên 1
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt("KEY_SAMPLE_INT", currentValue);
        editor.apply();  // Áp dụng các thay đổi
    }

    public static int getSampleInt(Context context) {
        SharedPreferences pre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        return pre.getInt("KEY_SAMPLE_INT", 0);
    }
}
