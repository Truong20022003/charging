package com.charging.animation.mobile.battery;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.internal.view.SupportMenu;



public class App extends Application {
    boolean isStartApp = false;


    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= 26) {
            createChanelID();
        }

        isStartApp = true;

    }



    @SuppressLint({"WrongConstant", "RestrictedApi"})
    @TargetApi(26)
    private void createChanelID() {
        try {
            String str = "my_channel_charging_animation";
            CharSequence string = getString(R.string.app_name);
            String string2 = getString(R.string.app_name);
            NotificationChannel notificationChannel = new NotificationChannel(str, string, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setDescription(string2);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        } catch (Exception e) {
        }
    }


}
