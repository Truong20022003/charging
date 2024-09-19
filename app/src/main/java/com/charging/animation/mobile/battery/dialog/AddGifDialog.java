package com.charging.animation.mobile.battery.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.util.DisplayMetrics;


import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.databinding.DialogAddGifBinding;
import com.charging.animation.mobile.battery.fragment.alarm.RingtoneAdapter;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.model.Ringtone;
import com.charging.animation.mobile.battery.util.SystemUtil;


import java.util.List;

public class AddGifDialog extends Dialog {
    Listener listener;
    DialogAddGifBinding binding;
    RingtoneAdapter adapter;
    List<Ringtone> list;
    Activity activity;
    public AddGifDialog(Context context, Listener listener, Activity activity){
        super(context, R.style.full_screen_dialog2);
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        SystemUtil.setLocale(getContext());
        getWindow().setNavigationBarColor(getContext().getResources().getColor(R.color.main));
        binding = DialogAddGifBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getAttributes().width = (int) (displayMetrics.widthPixels * 0.8);
        getWindow().getAttributes().height = (int) (displayMetrics.heightPixels*0.6);

    }

}
