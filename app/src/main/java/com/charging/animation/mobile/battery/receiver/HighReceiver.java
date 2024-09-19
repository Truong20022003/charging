package com.charging.animation.mobile.battery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.charging.animation.mobile.battery.service.HighService;

public class HighReceiver  extends BroadcastReceiver {
    public static boolean checkHigh = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        checkHigh = true;
        context.stopService(new Intent(context, HighService.class));
    }
}
