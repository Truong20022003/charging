package com.charging.animation.mobile.battery.activity.apply;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;


public class WallPaperSetUp {
    public static void setWallpaperLockScreen(Bitmap wallpaper, Context context){
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {

            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(wallpaper, screenWidth, screenHeight, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setBitmap(resizedBitmap,null,true,WallpaperManager.FLAG_LOCK);
            }else {
                wallpaperManager.setBitmap(resizedBitmap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setWallpaperHomeScreen(Bitmap wallpaper, Context context){
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(wallpaper, screenWidth, screenHeight, true);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setBitmap(resizedBitmap,null,true,WallpaperManager.FLAG_SYSTEM);
            }else {
                wallpaperManager.setBitmap(resizedBitmap);
//
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setWallpaperBoth(Bitmap wallpaper, Context context){
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(wallpaper);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
