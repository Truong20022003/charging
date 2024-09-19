# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/swati/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn com.squareup.picasso.**
# ---------- MẶC ĐỊNH CÓ -----------------

# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-ignorewarnings

# support v4
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }


# support-v7-appcompat
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }


# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule


# butter knife
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**


# google ads
-keep class com.google.android.gms.internal.** { *; }


# ServiceLoader support

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}
-keep class kotlinx.coroutines.android.** {*;}

# ---------- NẾU CALL API HOẶC DÙNG FIREBASE READTIME THÌ ADD CLASS VS MODEL SỬ DỤNG   -----------------


-keep class com.charging.animation.mobile.battery.api.** { *; }
-keep class com.charging.animation.mobile.battery.api.AdsModel.** { *; }
-keep class com.charging.animation.mobile.battery.api.Api.** { *; }
-keep class com.charging.animation.mobile.battery.api.ApiService.** { *; }
-keep class com.charging.animation.mobile.battery.api.Results.** { *; }
-keep class com.charging.animation.mobile.battery.api.RetrofitClient.** { *; }

-keep class com.charging.animation.mobile.battery.activity.apply.AnimContentActivity.** { *; }
-keep class com.charging.animation.mobile.battery.activity.base.BaseActivity.** { *; }
-keep class com.charging.animation.mobile.battery.App.** { *; }
-keep class com.charging.animation.mobile.battery.activity.splash.SplashActivity.** { *; }

-keep class com.amazic.ads.** { *; }
-keep class retrofit2.** { *; }

-keep class com.charging.animation.mobile.battery.fragment.home.model.HomeModel.** { *; }
-keep class com.charging.animation.mobile.battery.fragment.home.model.CallApi.** { *; }
-keep class com.charging.animation.mobile.battery.fragment.home.HomeFragment.** { *; }

-keep class com.charging.animation.mobile.battery.activity.down.DownActivity.** { *; }

-keep class com.charging.animation.mobile.battery.activity.ring.RingtoneActivity.** { *; }
-keep class com.charging.animation.mobile.battery.activity.ads.** { *; }
