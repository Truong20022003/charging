// Generated by view binder compiler. Do not edit!
package com.charging.animation.mobile.battery.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.custom.view.CustomTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCustomBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnHome;

  @NonNull
  public final ImageView imgNodata;

  @NonNull
  public final RecyclerView rcvGallery;

  @NonNull
  public final NestedScrollView rootNodata;

  @NonNull
  public final CustomTextView tvTitle;

  private FragmentCustomBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView btnHome,
      @NonNull ImageView imgNodata, @NonNull RecyclerView rcvGallery,
      @NonNull NestedScrollView rootNodata, @NonNull CustomTextView tvTitle) {
    this.rootView = rootView;
    this.btnHome = btnHome;
    this.imgNodata = imgNodata;
    this.rcvGallery = rcvGallery;
    this.rootNodata = rootNodata;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCustomBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCustomBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_custom, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCustomBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnHome;
      ImageView btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.imgNodata;
      ImageView imgNodata = ViewBindings.findChildViewById(rootView, id);
      if (imgNodata == null) {
        break missingId;
      }

      id = R.id.rcvGallery;
      RecyclerView rcvGallery = ViewBindings.findChildViewById(rootView, id);
      if (rcvGallery == null) {
        break missingId;
      }

      id = R.id.rootNodata;
      NestedScrollView rootNodata = ViewBindings.findChildViewById(rootView, id);
      if (rootNodata == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      CustomTextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new FragmentCustomBinding((ConstraintLayout) rootView, btnHome, imgNodata, rcvGallery,
          rootNodata, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
