package com.charging.animation.mobile.battery;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.charging.animation.mobile.battery.service.FullBatterService;

public class BackupWorker2  extends Worker {

    private static final String TAG = "BackupWorker";

    public BackupWorker2 (@NonNull Context context, @NonNull WorkerParameters workerParams ) {
        super ( context, workerParams );
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, FullBatterService.class));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

    @NonNull
    @Override
    public Result doWork () {
        return Result.success ();
    }
}