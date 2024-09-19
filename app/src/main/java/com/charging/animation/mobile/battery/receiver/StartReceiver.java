package com.charging.animation.mobile.battery.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.charging.animation.mobile.battery.BackupWorker;
import com.charging.animation.mobile.battery.service.BatteryService;
import com.charging.animation.mobile.battery.util.Data;

public class StartReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Data.getServiceBatter(context.getApplicationContext())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(BackupWorker.class).addTag("BACKUP_WORKER_TAG").build();
                WorkManager.getInstance(context).enqueue(request);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, BatteryService.class));
            } else {
                context.startService(new Intent(context, BatteryService.class));
            }
        }
    }
}
