package com.charging.animation.mobile.battery.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.util.SystemUtil;


public class RatingDialog extends Dialog {
    private OnPress onPress;
    private TextView tvTitle, tvContent,tvCancel;
    private RatingBar rtb;
    private EditText editFeedback;
    private Context context;
    private LinearLayout btnLater, btnWithMyPleasure, llButton;
    private LinearLayout btnRate;
    private ViewGroup rootBgButton;
    private int s;

    @SuppressLint("NonConstantResourceId")
    public RatingDialog(Context context2) {
        super(context2, R.style.BaseDialog);
        SystemUtil.setLocale(context2);
        this.context = context2;
        setContentView(R.layout.dialog_rating_app_2);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(attributes);
        getWindow().setSoftInputMode(16);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContentTutorial);
        rtb = findViewById(R.id.rtb);
        btnLater = findViewById(R.id.btnLater);
        btnRate = findViewById(R.id.btnRate);
        tvCancel = findViewById(R.id.tvCancel);
        btnWithMyPleasure = findViewById(R.id.btnWithMyPleasure);
        llButton = findViewById(R.id.ll_btn);

        funTextGradient(tvCancel);

        onclick();
        changeRating();

        btnWithMyPleasure.setOnClickListener(view -> {
            dismiss();
        } );


    }

    public interface OnPress {
        void send(int s);

        void rating(int s);

        void cancel();

        void later();
    }

    public void init(Context context, OnPress onPress) {
        this.onPress = onPress;
    }

    public void changeRating() {
        rtb.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            String getRating = String.valueOf(rtb.getRating());
            switch (getRating) {
                case "1.0":
                    s= 1;
                    break;
                case "2.0":
                    s = 2;
                    break;
                case "3.0":
                    s = 3;
                    break;
                case "4.0":
                    s = 4;
                    break;
                case "5.0":
                    s = 5;
                    break;
                default:
                    s = 0;
                    break;
            }
        });


    }

    public String getNewName() {
        return this.editFeedback.getText().toString();
    }

    public String getRating() {
        return String.valueOf(this.rtb.getRating());
    }

    public void onclick() {
        btnRate.setOnClickListener(view -> {
            if (rtb.getRating() == 0) {
                Toast.makeText(context, context.getResources().getString(R.string.Please_feedback), Toast.LENGTH_SHORT).show();
                return;
            }
            if (rtb.getRating() <= 3.0) {

                onPress.send(s);
            } else {

                onPress.rating(s);
            }

            tvTitle.setText(R.string.thank_you);
            tvContent.setText(R.string.for_supporting_our_service);
            btnWithMyPleasure.setVisibility(View.VISIBLE);
            rtb.setVisibility(View.GONE);
            llButton.setVisibility(View.GONE);

        });
        btnLater.setOnClickListener(view -> onPress.later());

    }

    void funTextGradient(TextView textView) {
        Paint paint = textView.getPaint();
        float width = paint.measureText(textView.getText().toString());
        Shader textShader = new LinearGradient(
                0f, 0f, width, textView.getTextSize(),
                new int[] {
                        Color.parseColor("#F16CFF"),
                        Color.parseColor("#12D6F0")
                },
                null, Shader.TileMode.CLAMP
        );
        paint.setShader(textShader);
        textView.invalidate();
    }

}

