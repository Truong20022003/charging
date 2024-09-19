package com.charging.animation.mobile.battery

import android.Manifest
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat

object Common {



    fun setLightMode(context: Context, open: Boolean?) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putBoolean("KEY_LIGHT_MODE", open!!).apply()
    }

    fun getLightMode(mContext: Context): Boolean {
        val preferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getBoolean("KEY_LIGHT_MODE", true)
    }

    fun setOverLay(context: Context, open: Boolean?) {
        val preferences = context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putBoolean("KEY_OVERLAY", open!!).apply()
    }

    fun getOverLay(mContext: Context): Boolean {
        val preferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getBoolean("KEY_OVERLAY", false)
    }

    fun setFirstOpen(context: Context, open: Boolean) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putBoolean("KEY_OPEN", open).apply()
    }

    fun getFirstOpen(mContext: Context): Boolean {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getBoolean("KEY_OPEN", true)
    }

    fun setOpenVersion(context: Context, version: Int) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        preferences.edit().putInt("KEY_VERSION", version).apply()
    }

    fun getOpenVersion(mContext: Context): Int {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)
        return preferences.getInt("KEY_VERSION", 0)
    }

    fun getLang(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_LANG", "en")
    }

    fun setLang(context: Context, open: String?) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_LANG", open).apply()
    }

    fun setPosition(context: Context, open: Int) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putInt("KEY_POSITION", open).apply()
    }

    fun getPosition(mContext: Context): Int {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getInt("KEY_POSITION", 0)
    }



    fun setPreLanguageflag(context: Context, flag: Int) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putInt("KEY_FLAG", flag).apply()
    }

    fun setFirstUserPinLock(context: Context, open: Boolean) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putBoolean("KEY_FIRST_PIN_LOCK", open).apply()
    }

    fun getFirstUserPinLock(mContext: Context): Boolean {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getBoolean("KEY_FIRST_PIN_LOCK", true)
    }

    fun setFirstUserPatternLock(context: Context, open: Boolean) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putBoolean("KEY_FIRST_PATTERN_LOCK", open).apply()
    }

    fun getFirstUserPatternLock(mContext: Context): Boolean {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getBoolean("KEY_FIRST_PATTERN_LOCK", true)
    }

    fun checkOverlayPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(context)
        } else {
            true
        }
    }

    fun checkMediaFilePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == 0
    }

}