package com.charging.animation.mobile.battery.service;
import static androidx.core.app.NotificationCompat.PRIORITY_MAX;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;


import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.charging.animation.mobile.battery.NotificationBattery;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.receiver.FullReceiver;
import com.charging.animation.mobile.battery.util.Data;


public class FullBatterService extends Service {

    public static  MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        if(Data.getSelectRingtone(getApplicationContext())!=null){
            try{
                Uri notification =  Uri.parse(Data.getSelectRingtone(getApplicationContext()));
                mediaPlayer = MediaPlayer.create(getApplicationContext(), notification);
                mediaPlayer.start();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        showNotification();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       if(mediaPlayer!=null){
           mediaPlayer.stop();
       }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification() {

        Intent mMainIntent = new Intent(getBaseContext(), FullReceiver.class);
        mMainIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent mMainPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2001, mMainIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        RemoteViews contentView = new RemoteViews(getApplicationContext().getPackageName(), R.layout.service_full_batter);
        contentView.setTextViewText(R.id.tvTitle, getString(R.string.full_charging_tap_to_stop_ringtone));
        contentView.setOnClickPendingIntent(R.id.message, mMainPendingIntent);


        startForeground(NotificationBattery.NOTIFICATION_BATTERY_ID, NotificationBattery.getInstance(this).build() );
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? createNotificationChannel(notificationManager) : "";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.splash_img_logo)
                .setPriority(PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContent(contentView)
                .setCustomContentView(contentView)
                .build();

        startForeground(2001, notification);

    }



    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager){
        String channelId = "Full";
        String channelName = "Charging service full";
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.setImportance(NotificationManager.IMPORTANCE_NONE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }



}
