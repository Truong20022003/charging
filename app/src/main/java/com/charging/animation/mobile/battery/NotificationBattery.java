package com.charging.animation.mobile.battery;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class NotificationBattery  extends NotificationCompat.Builder   {
    public static final int NOTIFICATION_BATTERY_ID = 1;

    NotificationManager notificationManager;
    private static NotificationBattery notificationBattery;
    Context mContext;

    public NotificationBattery(@NonNull Context context, @NonNull String channelId) {
        super(context, channelId);
        this.mContext = context.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            this.setChannelId(channelId);
        }
    }


    public static NotificationBattery getInstance(Context context) {
        if (notificationBattery == null)
            notificationBattery = new NotificationBattery(context,"my_channel_charging_animation");
        return notificationBattery;
    }


}


