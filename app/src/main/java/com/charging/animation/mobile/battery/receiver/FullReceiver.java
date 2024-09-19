package com.charging.animation.mobile.battery.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.charging.animation.mobile.battery.service.FullBatterService;

public class FullReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.stopService(new Intent(context, FullBatterService.class));
    }
}
