package com.charging.animation.mobile.battery.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.databinding.DialogExitBinding;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.SharePrefUtils;
import com.charging.animation.mobile.battery.util.SystemUtil;

public class ExitDialog extends Dialog {
    Listener listener;
    DialogExitBinding binding;
    Activity activity;
    public ExitDialog(Context context, Listener listener, Activity activity){
        super(context, R.style.full_screen_dialog2);
        this.listener = listener;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtil.setLocale(getContext());
        getWindow().setNavigationBarColor(getContext().getResources().getColor(R.color.main));
        binding = DialogExitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().getAttributes().width = (int) (displayMetrics.widthPixels * 0.9);
        getWindow().getAttributes().height = WindowManager.LayoutParams.WRAP_CONTENT;

        if (SharePrefUtils.isRated(activity)) {
            binding.root.setVisibility(View.GONE);
            binding.root2.setVisibility(View.VISIBLE);
        }else {
            binding.root.setVisibility(View.VISIBLE);
            binding.root2.setVisibility(View.GONE);
        }

        binding.btnApply.setOnClickListener(v -> {
            dismiss();
            listener.onCancel();
        });

        binding.btnApply2.setOnClickListener(v -> {
            dismiss();
            listener.onCancel();
        });

        binding.btnRAte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onRate();
            }
        });

        binding.btnFeedback.setOnClickListener(v -> {
            dismiss();
            listener.onExit();
        });

        binding.btnFeedback2.setOnClickListener(v -> {
            dismiss();
            listener.onExit();
        });

    }
}
