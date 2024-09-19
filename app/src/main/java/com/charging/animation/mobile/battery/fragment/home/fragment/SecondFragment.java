package com.charging.animation.mobile.battery.fragment.home.fragment;

import static android.content.Context.BATTERY_SERVICE;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.charging.animation.mobile.battery.base.BaseFragment;
import com.charging.animation.mobile.battery.databinding.FragmentSecondUpdateBinding;
import com.charging.animation.mobile.battery.model.Battery;
import com.charging.animation.mobile.battery.receiver.AnimationReceiver;
import com.charging.animation.mobile.battery.util.Util;

public class SecondFragment extends BaseFragment<FragmentSecondUpdateBinding> {

    Battery battery;
    boolean isCharging;
    @NonNull
    @Override
    public FragmentSecondUpdateBinding getViewBinding() {
        return FragmentSecondUpdateBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        battery = new Battery();



        BatteryManager batteryManager = (BatteryManager) requireActivity().getSystemService(BATTERY_SERVICE);
        boolean status = false;


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            try {
                int chargingStatus = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS);

                status = chargingStatus == BatteryManager.BATTERY_STATUS_CHARGING
                        || chargingStatus == BatteryManager.BATTERY_STATUS_FULL;
            }catch (Exception exception){
                exception.printStackTrace();
            }


        }

        if (status ) {
            AnimationReceiver.check = 1;
            binding.connect.setVisibility(View.VISIBLE);
        } else {
            AnimationReceiver.check = 0;
            binding.connect.setVisibility(View.GONE);
        }


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        requireActivity().registerReceiver(receiver, filter);

        if(AnimationReceiver.check == 1){
            binding.connect.setVisibility(View.VISIBLE);
        }else {
            binding.connect.setVisibility(View.GONE);
        }

        binding.tvBattery.setSelected(true);

        binding.tv1.setSelected(true);
        binding.tv2.setSelected(true);
        binding.tv3.setSelected(true);
        binding.tv4.setSelected(true);
        binding.tvHeart.setSelected(true);
        binding.template.setSelected(true);
        binding.tvCapacity.setSelected(true);
        binding.tvLion.setSelected(true);

        binding.tv1.setText(binding.tv1.getText().toString()+":");
        binding.tv2.setText(binding.tv2.getText().toString()+":");
        binding.tv3.setText(binding.tv3.getText().toString()+":");
        binding.tv4.setText(binding.tv4.getText().toString()+":");
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {

                battery.level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                battery.scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                battery.temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                battery.voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                battery.technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                battery.status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

//                Log.e("Battery","EXTRA_LEVEL: "+battery.level);
//                Log.e("Battery","EXTRA_SCALE: "+battery.scale);
//                Log.e("Battery","EXTRA_TEMPERATURE: "+battery.temperature);
//                Log.e("Battery","EXTRA_VOLTAGE: "+battery.voltage);
//                Log.e("Battery","EXTRA_TECHNOLOGY: "+battery.technology);
//                Log.e("Battery","EXTRA_STATUS: "+battery.status);
//
//                Log.e("Battery","-----------------------------------------------------");
//                Log.e("Battery","Loại sạc: "+ Util.getChargeType(context));
//                Log.e("Battery","Nhiệt độ: "+Util.getTemp(battery.temperature, true));
//                Log.e("Battery","Tổng pin: "+Util.getBatteryCapacity(context));
//                Log.e("Battery","Vol: "+Util.getVol(battery.voltage));

                binding.template.setText(Util.getTemp(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0),true));//+" / "+Util.getTemp(intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0),false));
//                binding.tvVol.setText(Util.getVol( intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1))+" V");
//                binding.tvType.setText(Util.getChargeType(context));
                binding.tvCapacity.setText(Util.getBatteryCapacity(context)+"mah");
                binding.tvLion.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
                //binding.tvHeart

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryPct = (int) (level * 100 / (float)scale);
                binding.tvPin3.setText(batteryPct+"%");
                status(batteryPct);

                if (battery.status == BatteryManager.BATTERY_STATUS_FULL){
                   // Log.e("Battery","BATTERY_STATUS_FULL");
                }

                isCharging = battery.status == BatteryManager.BATTERY_STATUS_CHARGING || battery.status == BatteryManager.BATTERY_STATUS_FULL;
            }
            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                AnimationReceiver.check = 1;
                binding.connect.setVisibility(View.VISIBLE);
            }
            if( action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                if( AnimationReceiver.check == 1){
                    binding.connect.setVisibility(View.GONE);
                    AnimationReceiver.check = 0;
                }
                binding.connect.setVisibility(View.GONE);

            }
        }
    };

    void status(int n){
//        binding.tvPinInfo.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                binding.tvPinInfo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                final int[] h = {binding.tvPinInfo.getHeight()};
//                h[0] = (n* h[0])/100;
//
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, h[0]);
//                layoutParams.gravity = Gravity.BOTTOM;
//                binding.imgPin.setLayoutParams(layoutParams);
//                binding.imgPin.requestLayout();
//
//                if(n>=72){
//                    binding.imgPin.setBackgroundResource(R.drawable.bg_batter_info_1);
//                    binding.tvHeart.setText(getString(R.string.good));
//                }else if(n>=21){
//                    binding.imgPin.setBackgroundResource(R.drawable.bg_batter_info_2);
//                    binding.tvHeart.setText(getString(R.string.waring));
//                }else {
//                    binding.imgPin.setBackgroundResource(R.drawable.bg_batter_info_3);
//                    binding.tvHeart.setText(getString(R.string.low));
//                }
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        requireActivity().unregisterReceiver(receiver);
    }

}

