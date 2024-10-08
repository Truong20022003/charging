// Generated by view binder compiler. Do not edit!
package com.charging.animation.mobile.battery.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.charging.animation.mobile.battery.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRingtoneUpdateBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnBack3;

  @NonNull
  public final FrameLayout frAds;

  @NonNull
  public final ImageView imgBack;

  @NonNull
  public final RecyclerView rcvRingtone;

  @NonNull
  public final ConstraintLayout toolbar;

  @NonNull
  public final TextView tvTitle;

  private ActivityRingtoneUpdateBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView btnBack3, @NonNull FrameLayout frAds, @NonNull ImageView imgBack,
      @NonNull RecyclerView rcvRingtone, @NonNull ConstraintLayout toolbar,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnBack3 = btnBack3;
    this.frAds = frAds;
    this.imgBack = imgBack;
    this.rcvRingtone = rcvRingtone;
    this.toolbar = toolbar;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRingtoneUpdateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRingtoneUpdateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_ringtone_update, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRingtoneUpdateBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBack3;
      ImageView btnBack3 = ViewBindings.findChildViewById(rootView, id);
      if (btnBack3 == null) {
        break missingId;
      }

      id = R.id.fr_ads;
      FrameLayout frAds = ViewBindings.findChildViewById(rootView, id);
      if (frAds == null) {
        break missingId;
      }

      id = R.id.imgBack;
      ImageView imgBack = ViewBindings.findChildViewById(rootView, id);
      if (imgBack == null) {
        break missingId;
      }

      id = R.id.rcvRingtone;
      RecyclerView rcvRingtone = ViewBindings.findChildViewById(rootView, id);
      if (rcvRingtone == null) {
        break missingId;
      }

      id = R.id.toolbar;
      ConstraintLayout toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ActivityRingtoneUpdateBinding((ConstraintLayout) rootView, btnBack3, frAds,
          imgBack, rcvRingtone, toolbar, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
