package com.charging.animation.mobile.battery.dialog;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.apply.adapter.SettingAdapter;
import com.charging.animation.mobile.battery.activity.apply.model.Setting;
import com.charging.animation.mobile.battery.api.CommonAds;
import com.charging.animation.mobile.battery.databinding.DialogSettingBinding;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.Data;
import com.charging.animation.mobile.battery.util.SharePrefUtils;
import com.charging.animation.mobile.battery.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

public class DialogSetting extends Dialog {
    Listener listener;
    DialogSettingBinding binding;
    Activity activity;
    int time, off;
    public DialogSetting(Context context, Listener listener, Activity activity){
        super(context, R.style.full_screen_dialog2);
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtil.setLocale(getContext());
        binding = DialogSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        try{
            getWindow().getAttributes().width = (int) (displayMetrics.widthPixels * 0.9);
            getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;
        }catch (Exception exception){
            exception.printStackTrace();
        }


        time = Data.getSettingTime(getContext());
        off = Data.getSettingOff(getContext());



        switch (time){
            case 0:{
                binding.tvSecond.setText(activity.getString(R.string._5_seconds));
                break;
            }
            case 1:{
                binding.tvSecond.setText(activity.getString(R.string._10_seconds));
                break;
            }
            case 2:{
                binding.tvSecond.setText(activity.getString(R.string._15_seconds));
                break;
            }
            case 3:{
                binding.tvSecond.setText(activity.getString(R.string._20_seconds));
                break;
            }
            case 4:{
                binding.tvSecond.setText(activity.getString(R.string._25_seconds));
                break;
            }
            case 5:{
                binding.tvSecond.setText(activity.getString(R.string._30_seconds));
                break;
            }
        }


        switch (off){
            case 0:{
                binding.tvClose.setText(activity.getString(R.string.single_tap_to_hide));
                break;
            }
            case 1:{
                binding.tvClose.setText(activity.getString(R.string.double_tap_to_hide));
                break;
            }
        }

       binding.constraintLayout4.setOnClickListener(v->{
           popupMenu();
       });

       binding.constraintLayout5.setOnClickListener(v -> popupMenu2());

       binding.btnFeedback.setOnClickListener(v -> {
           switch (time){
               case 0:{
                   Data.setSettingTime(getContext(),0);
                   break;
               }
               case 1:{
                   Data.setSettingTime(getContext(),1);
                   break;
               }
               case 2:{
                   Data.setSettingTime(getContext(),2);
                   break;
               }
               case 3:{
                   Data.setSettingTime(getContext(),3);
                   break;
               }
               case 4:{
                   Data.setSettingTime(getContext(),4);
                   break;
               }
               case 5:{
                   Data.setSettingTime(getContext(),5);
                   break;
               }
           }
           switch (off){
               case 0:{
                   Data.setSettingOff(getContext(),0);
                   break;
               }
               case 1:{
                   Data.setSettingOff(getContext(),1);
                   break;
               }
           }
           dismiss();
           listener.onApply();
           SharePrefUtils.setSampleInt(activity);
           CommonAds.logEvent(activity, "animation_apply_next_click");
       });

       binding.btnApply.setOnClickListener(v -> dismiss());

    }

    private void popupMenu() {
        LayoutInflater inflater = (LayoutInflater)
               activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        PopupWindow popupWindow = new PopupWindow(getContext());
        popupWindow.setWidth(binding.constraintLayout4.getWidth());
        View popupView = inflater.inflate(R.layout.item_menu_country, null);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setContentView(popupView);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(binding.constraintLayout4, 0, 0);

        List<Setting> list = new ArrayList<>();

        list.add(new Setting(activity.getString(R.string._5_seconds), false));
        list.add(new Setting(activity.getString(R.string._10_seconds), false));
        list.add(new Setting(activity.getString(R.string._15_seconds), false));
        list.add(new Setting(activity.getString(R.string._20_seconds), false));
        list.add(new Setting(activity.getString(R.string._25_seconds), false));
        list.add(new Setting(activity.getString(R.string._30_seconds), false));


        list.get(time).check = true;


        RecyclerView recyclerView = popupView.findViewById(R.id.rcvCountry);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(new SettingAdapter(activity, list, select -> {
            time = select;
            switch (time){
                case 0:{
                    binding.tvSecond.setText(activity.getString(R.string._5_seconds));
                    CommonAds.logEvent(activity, "animation_select_time_click", "value", "5_seconds");
                    break;
                }
                case 1:{
                    binding.tvSecond.setText(activity.getString(R.string._10_seconds));
                    CommonAds.logEvent(activity, "animation_select_time_click", "value", "10_seconds");
                    break;
                }
                case 2:{
                    binding.tvSecond.setText(activity.getString(R.string._15_seconds));
                    CommonAds.logEvent(activity, "animation_select_time_click", "value", "15_seconds");
                    break;
                }
                case 3:{
                    binding.tvSecond.setText(activity.getString(R.string._20_seconds));
                    CommonAds.logEvent(activity, "animation_select_time_click", "value", "20_seconds");
                    break;
                }
                case 4:{
                    binding.tvSecond.setText(activity.getString(R.string._25_seconds));
                    CommonAds.logEvent(activity, "animation_select_time_click", "value", "25_seconds");
                    break;
                }
                case 5:{
                    binding.tvSecond.setText(activity.getString(R.string._30_seconds));
                    CommonAds.logEvent(activity, "animation_select_time_click", "value", "30_seconds");
                    break;
                }
            }

            popupWindow.dismiss();
        }));


        popupWindow.setOnDismissListener(() -> {

        });

    }

    private void popupMenu2() {
        LayoutInflater inflater = (LayoutInflater)
                activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        PopupWindow popupWindow = new PopupWindow(getContext());
        popupWindow.setWidth(binding.constraintLayout5.getWidth());
        View popupView = inflater.inflate(R.layout.item_menu_country, null);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setContentView(popupView);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(binding.constraintLayout5, 0, 0);

        List<Setting> list = new ArrayList<>();

        list.add(new Setting(activity.getString(R.string.single_tap_to_hide), false));
        list.add(new Setting(activity.getString(R.string.double_tap_to_hide), false));

        list.get(off).check = true;


        RecyclerView recyclerView = popupView.findViewById(R.id.rcvCountry);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(new SettingAdapter(activity, list, select -> {
            off = select;
            switch (off){
                case 0:{
                    CommonAds.logEvent(activity, "animation_select_closing_method", "value", "single tap to hide");
                    binding.tvClose.setText(activity.getString(R.string.single_tap_to_hide));
                    break;
                }
                case 1:{
                    CommonAds.logEvent(activity, "animation_select_closing_method", "value", "double tap to hide");
                    binding.tvClose.setText(activity.getString(R.string.double_tap_to_hide));
                    break;
                }
            }
            popupWindow.dismiss();
        }));


        popupWindow.setOnDismissListener(() -> {

        });

    }


}
