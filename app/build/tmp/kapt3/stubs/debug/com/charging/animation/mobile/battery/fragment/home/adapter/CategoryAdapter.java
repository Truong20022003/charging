package com.charging.animation.mobile.battery.fragment.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.databinding.ItemFragmentHomeItemCategoryBinding;
import com.charging.animation.mobile.battery.fragment.home.model.CategoryModel;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.util.Constants;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0014H\u0016J\u0018\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0014H\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/home/adapter/CategoryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/charging/animation/mobile/battery/fragment/home/adapter/CategoryAdapter$CategoryViewHolder;", "context", "Landroid/content/Context;", "listener", "Lcom/charging/animation/mobile/battery/listener/Listener;", "(Landroid/content/Context;Lcom/charging/animation/mobile/battery/listener/Listener;)V", "list", "", "Lcom/charging/animation/mobile/battery/fragment/home/model/CategoryModel;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "getListener", "()Lcom/charging/animation/mobile/battery/listener/Listener;", "setListener", "(Lcom/charging/animation/mobile/battery/listener/Listener;)V", "positionOld", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "refresh", "CategoryViewHolder", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class CategoryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.charging.animation.mobile.battery.fragment.home.adapter.CategoryAdapter.CategoryViewHolder> {
    private android.content.Context context;
    @org.jetbrains.annotations.NotNull
    private com.charging.animation.mobile.battery.listener.Listener listener;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.charging.animation.mobile.battery.fragment.home.model.CategoryModel> list;
    private int positionOld = 0;
    
    public CategoryAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.listener.Listener listener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.charging.animation.mobile.battery.listener.Listener getListener() {
        return null;
    }
    
    public final void setListener(@org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.listener.Listener p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.fragment.home.model.CategoryModel> getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.fragment.home.model.CategoryModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.charging.animation.mobile.battery.fragment.home.adapter.CategoryAdapter.CategoryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.fragment.home.adapter.CategoryAdapter.CategoryViewHolder holder, int position) {
    }
    
    private final void refresh(int position) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/home/adapter/CategoryAdapter$CategoryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/charging/animation/mobile/battery/databinding/ItemFragmentHomeItemCategoryBinding;", "(Lcom/charging/animation/mobile/battery/databinding/ItemFragmentHomeItemCategoryBinding;)V", "getBinding", "()Lcom/charging/animation/mobile/battery/databinding/ItemFragmentHomeItemCategoryBinding;", "bind", "", "data", "Lcom/charging/animation/mobile/battery/fragment/home/model/CategoryModel;", "context", "Landroid/content/Context;", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
    public static final class CategoryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.charging.animation.mobile.battery.databinding.ItemFragmentHomeItemCategoryBinding binding = null;
        
        public CategoryViewHolder(@org.jetbrains.annotations.NotNull
        com.charging.animation.mobile.battery.databinding.ItemFragmentHomeItemCategoryBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.charging.animation.mobile.battery.databinding.ItemFragmentHomeItemCategoryBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.charging.animation.mobile.battery.fragment.home.model.CategoryModel data, @org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
    }
}