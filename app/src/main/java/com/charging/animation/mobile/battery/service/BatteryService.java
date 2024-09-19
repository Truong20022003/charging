package com.charging.animation.mobile.battery.service;

import static android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE;
import static androidx.core.app.NotificationCompat.PRIORITY_MAX;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.main.MainActivity;
import com.charging.animation.mobile.battery.receiver.AnimationReceiver;
import com.charging.animation.mobile.battery.receiver.HighReceiver;
import com.charging.animation.mobile.battery.receiver.LowReceiver;
import com.charging.animation.mobile.battery.receiver.StartReceiver;
import com.charging.animation.mobile.battery.util.Data;


public class BatteryService extends Service {

    private AnimationReceiver animationReceiver;
    private static final String NOTIFICATION_CHANNEL_ID = "Charging";

    @Override
    public void onCreate() {
        super.onCreate();
        @SuppressLint("RemoteViewLayout") RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.layout_notification);
        showNotification(contentView);
        registerTimeReceiver();
        service();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (animationReceiver != null){
            unregisterReceiver(animationReceiver);
            animationReceiver =null;
        }
        registerBetterReceiver();
        registerTimeReceiver();
        return START_STICKY;
    }

    private void registerTimeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.registerReceiver(this.timeReceiver, intentFilter, RECEIVER_NOT_EXPORTED);
        }else {
            this.registerReceiver(this.timeReceiver, intentFilter);
        }


    }


    private final BroadcastReceiver timeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            service();
        }
    };

    void service(){
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getBaseContext().registerReceiver(null, intentFilter);
        int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);

        float temperatureCelsius = temperature / 10.0f;
        if(temperatureCelsius >= 60){
            if(!HighReceiver.checkHigh && Data.getHigh(getBaseContext())){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    getBaseContext().startForegroundService(new Intent(getBaseContext(), HighService.class));
                } else {
                    getBaseContext().startService(new Intent(getBaseContext(), HighService.class));
                }
            }

        }else {
            HighReceiver.checkHigh = false;
        }

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = (level / (float) scale) * 100;

        if(batteryPct <= 20){
            if(!LowReceiver.checkStopLow && Data.getLow(getBaseContext())){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    getBaseContext().startForegroundService(new Intent(getBaseContext(), LowService.class));
                } else {
                    getBaseContext().startService(new Intent(getBaseContext(), LowService.class));
                }
            }

        }else {
            LowReceiver.checkStopLow = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            if (animationReceiver != null){
                unregisterReceiver(animationReceiver);
                animationReceiver =null;
            }
            if(Data.getServiceBatter(getApplicationContext())){
                getApplicationContext().sendBroadcast(new Intent(getApplicationContext(), StartReceiver.class));
            }

            unregisterReceiver(timeReceiver);
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    private void registerBetterReceiver() {
        animationReceiver = new AnimationReceiver();
        IntentFilter filter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        this.registerReceiver(this.animationReceiver, filter);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Charging Animation";
            String description = "Background service";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(RemoteViews remoteViews) {
        this.createNotificationChannel();

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.drawable.splash_img_logo)
                        .setPriority(PRIORITY_MAX)
                        .setCategory(NotificationCompat.CATEGORY_SERVICE)
                        .setContent(remoteViews)
                        .setCustomContentView(remoteViews);

        builder.setOngoing(true);

        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE
                );
        builder.setContentIntent(resultPendingIntent);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                startForeground(1234, builder.build(), FOREGROUND_SERVICE_TYPE_SPECIAL_USE);
            }else {
                startForeground(1234, builder.build());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
