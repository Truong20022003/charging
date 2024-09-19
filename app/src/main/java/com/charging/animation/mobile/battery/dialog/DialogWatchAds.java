package com.charging.animation.mobile.battery.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.databinding.DialogWatchAdsBinding;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.SystemUtil;

public class DialogWatchAds extends Dialog {
    Listener listener;
    DialogWatchAdsBinding binding;
    Activity activity;
    boolean ads;
    public DialogWatchAds(Context context, Listener listener, Activity activity){
        super(context, R.style.full_screen_dialog2);
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtil.setLocale(getContext());
        getWindow().setNavigationBarColor(getContext().getResources().getColor(R.color.main));
        binding = DialogWatchAdsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(attributes);

//        binding.imgSelectAds.setOnClickListener(v -> {
//            if(!ads){
//                binding.imgSelectAds.setImageResource(R.drawable.ic_select);
//                Data.setADS(activity, true);
//            }else {
//                binding.imgSelectAds.setImageResource(R.drawable.ic_unselect);
//                Data.setADS(activity, false);
//            }
//            ads = !ads;
//        });

        binding.btnApply.setOnClickListener(v -> {
            dismiss();
            listener.onClick(ads = !ads);
        });

    }

}
