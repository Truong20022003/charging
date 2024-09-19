package com.charging.animation.mobile.battery.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://207.148.116.90/";
    @GET("/api/v020_charging_animation/{folder}")
    Call<List<Results>> getAll(@Path("folder") String folder);

    @GET("/api/v020_charging_animation/all")
    Call<List<Results>> getAll();
}
