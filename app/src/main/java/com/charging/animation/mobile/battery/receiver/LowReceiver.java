package com.charging.animation.mobile.battery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.charging.animation.mobile.battery.service.LowService;

public class LowReceiver extends BroadcastReceiver {
    public static boolean checkStopLow = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        checkStopLow = true;
        context.stopService(new Intent(context, LowService.class));
    }
}
