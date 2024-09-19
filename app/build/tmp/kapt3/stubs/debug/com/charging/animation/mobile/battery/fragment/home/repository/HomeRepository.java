package com.charging.animation.mobile.battery.fragment.home.repository;

import android.content.Context;
import com.charging.animation.mobile.battery.api.Api;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.api.RetrofitClient;
import com.charging.animation.mobile.battery.database.Database;
import com.charging.animation.mobile.battery.fragment.home.listener.Listener;
import com.charging.animation.mobile.battery.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J(\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u00107\u001a\u0004\u0018\u000101J\u0006\u00108\u001a\u000203J\u0018\u00109\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\u0006\u0010:\u001a\u00020;R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0007\"\u0004\b!\u0010\tR \u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0007\"\u0004\b$\u0010\tR \u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0007\"\u0004\b\'\u0010\tR \u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010\tR \u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\t\u00a8\u0006<"}, d2 = {"Lcom/charging/animation/mobile/battery/fragment/home/repository/HomeRepository;", "", "()V", "listAll", "", "Lcom/charging/animation/mobile/battery/api/Results;", "getListAll", "()Ljava/util/List;", "setListAll", "(Ljava/util/List;)V", "listAnimal", "getListAnimal", "setListAnimal", "listBlack", "getListBlack", "setListBlack", "listCat", "getListCat", "setListCat", "listChill", "getListChill", "setListChill", "listFantasy", "getListFantasy", "setListFantasy", "listFire", "getListFire", "setListFire", "listFlower", "getListFlower", "setListFlower", "listHanddrawing", "getListHanddrawing", "setListHanddrawing", "listHorror", "getListHorror", "setListHorror", "listLofi", "getListLofi", "setListLofi", "listNeon", "getListNeon", "setListNeon", "listRecommend", "getListRecommend", "setListRecommend", "check", "", "link", "", "checkDownSuccess", "", "context", "Landroid/content/Context;", "data", "folder", "clear", "getAllApi", "listener", "Lcom/charging/animation/mobile/battery/fragment/home/listener/Listener;", "ChargingAnimation_v1.0.0_09.19.2024_debug"})
public final class HomeRepository {
    @org.jetbrains.annotations.NotNull
    public static final com.charging.animation.mobile.battery.fragment.home.repository.HomeRepository INSTANCE = null;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listAll;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listRecommend;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listBlack;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listCat;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listChill;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listFantasy;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listFire;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listFlower;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listHanddrawing;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listHorror;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listLofi;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listNeon;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.charging.animation.mobile.battery.api.Results> listAnimal;
    
    private HomeRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListAll() {
        return null;
    }
    
    public final void setListAll(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListRecommend() {
        return null;
    }
    
    public final void setListRecommend(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListBlack() {
        return null;
    }
    
    public final void setListBlack(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListCat() {
        return null;
    }
    
    public final void setListCat(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListChill() {
        return null;
    }
    
    public final void setListChill(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListFantasy() {
        return null;
    }
    
    public final void setListFantasy(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListFire() {
        return null;
    }
    
    public final void setListFire(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListFlower() {
        return null;
    }
    
    public final void setListFlower(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListHanddrawing() {
        return null;
    }
    
    public final void setListHanddrawing(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListHorror() {
        return null;
    }
    
    public final void setListHorror(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListLofi() {
        return null;
    }
    
    public final void setListLofi(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListNeon() {
        return null;
    }
    
    public final void setListNeon(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.charging.animation.mobile.battery.api.Results> getListAnimal() {
        return null;
    }
    
    public final void setListAnimal(@org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> p0) {
    }
    
    public final void getAllApi(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.charging.animation.mobile.battery.fragment.home.listener.Listener listener) {
    }
    
    public final void checkDownSuccess(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<com.charging.animation.mobile.battery.api.Results> data, @org.jetbrains.annotations.Nullable
    java.lang.String folder) {
    }
    
    public final boolean check(@org.jetbrains.annotations.NotNull
    java.lang.String link) {
        return false;
    }
    
    public final void clear() {
    }
}