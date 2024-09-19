package com.charging.animation.mobile.battery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.charging.animation.mobile.battery.BackupWorker2;
import com.charging.animation.mobile.battery.activity.animation.AnimationActivity;
import com.charging.animation.mobile.battery.service.FullBatterService;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.Util;

public class AnimationReceiver extends BroadcastReceiver {
    public static int check = 0;
    static int checkFull = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(Data.getServiceBatter(context)){
            if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
               try{
                   check = 1;
                   checkFull = 0;
                   Intent intent1 = new Intent(context.getApplicationContext(), AnimationActivity.class);
                   intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.getApplicationContext().startActivity(intent1);
                   //Toast.makeText(context, "ACTION_POWER_CONNECTED", Toast.LENGTH_SHORT).show();
               }catch (Exception exception){
                   exception.printStackTrace();
               }
            }
            if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                if (check == 1) {
                    try{
                        Intent stopAnim = new Intent();
                        stopAnim.setAction(AnimationActivity.ACTION_STOP_ANIMATION);
                        if(AnimationActivity.ACTIVE){
                            context.getApplicationContext().sendBroadcast(stopAnim);
                        }
                        check = 0;
                        context.getApplicationContext().stopService(new Intent(context,FullBatterService.class));
                        //Toast.makeText(context.getApplicationContext(), "ACTION_POWER_DISCONNECTED", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        }


        if (Data.getServiceBatterFull(context) && check == 1 && checkFull == 0) {
            try{
                if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                    int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                    int batteryPct = (int) (level * 100 / (float) scale);
                    if (batteryPct == 100) {
                        if(!Util.isMyServiceRunning(FullBatterService.class,context)){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(BackupWorker2.class).addTag("BACKUP_WORKER_TAG").build();
                                WorkManager.getInstance(context).enqueue(request);
                            } else
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                context.startForegroundService(new Intent(context, FullBatterService.class));
                            } else {
                                context.startService(new Intent(context, FullBatterService.class));
                            }
                        }
                        checkFull = 1;
                    }
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            try{
                context.getApplicationContext().stopService(new Intent(context,FullBatterService.class));
                Intent stopAnim = new Intent();
                stopAnim.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                stopAnim.setAction(AnimationActivity.ACTION_STOP_ANIMATION);
                if(AnimationActivity.ACTIVE){
                    context.getApplicationContext().sendBroadcast(stopAnim);
                }
                check = 0;

            }catch (Exception e){

            }
        }


    }
}
