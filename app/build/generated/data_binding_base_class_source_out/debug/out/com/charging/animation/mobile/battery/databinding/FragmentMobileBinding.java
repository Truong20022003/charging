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
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMobileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imgCycle;

  @NonNull
  public final RecyclerView rcvMobile;

  @NonNull
  public final NestedScrollView scrollView2;

  private FragmentMobileBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imgCycle,
      @NonNull RecyclerView rcvMobile, @NonNull NestedScrollView scrollView2) {
    this.rootView = rootView;
    this.imgCycle = imgCycle;
    this.rcvMobile = rcvMobile;
    this.scrollView2 = scrollView2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMobileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMobileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_mobile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMobileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgCycle;
      ImageView imgCycle = ViewBindings.findChildViewById(rootView, id);
      if (imgCycle == null) {
        break missingId;
      }

      id = R.id.rcvMobile;
      RecyclerView rcvMobile = ViewBindings.findChildViewById(rootView, id);
      if (rcvMobile == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      NestedScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      return new FragmentMobileBinding((ConstraintLayout) rootView, imgCycle, rcvMobile,
          scrollView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
