package com.charging.animation.mobile.battery.util

import android.content.Context

object SharedPreferencesRepository {

   const val PREF_NAME = "SharedPreferencesRepository"
    const val INTRO = "intro"
    const val LANGUAGE = "language"
    const val RATE = "rate"
    const val PRESS_HOLD_MODE_STOP_AFTER = "PRESS_HOLD_MODE_STOP_AFTER" // Bằng 0 vô hạn, bằng 1 theo thời gian để tự tắt, bằng 2 số lần thực hiện
    const val PRESS_HOLD_MODE_PRESS_DURATION = "PRESS_HOLD_MODE_PRESS_DURATION"
    const val PRESS_HOLD_MODE_CLICK_INTERVAL_TIME = "PRESS_HOLD_MODE_CLICK_INTERVAL_TIME"
    const val PRESS_HOLD_MODE_CLICK_INTERVAL_MODE = "PRESS_HOLD_MODE_CLICK_INTERVAL_MODE" // Bằng 0 mili giây, bằng 1 theo giây, bằng 2 phút
    const val PRESS_HOLD_HOUR = "PRESS_HOLD_HOUR" // Giờ
    const val PRESS_HOLD_MINUTE = "PRESS_HOLD_MINUTE" // Phút
    const val PRESS_HOLD_SECOND = "PRESS_HOLD_SECOND" // Giây
    const val PRESS_HOLD_SUM_LOOP = "PRESS_HOLD_SUM_LOOP" // Tổng vòng lặp
    const val DATA_PRESS_HOLD = "DataPressHold1.json" // Tên cơ sở dữ liệu

    const val PRESS_HOLD_MODE_PRESS_DURATION_DEFAULT = 10000L
    const val PRESS_HOLD_MODE_CLICK_INTERVAL_TIME_DEFAULT = 1000L
    const val PRESS_HOLD_MODE_STOP_AFTER_DEFAULT = 0
    const val PRESS_HOLD_SUM_LOOP_DEFAULT = 10
    const val PRESS_HOLD_MODE_CLICK_INTERVAL_MODE_DEFAULT = 0
    const val PRESS_HOLD_HOUR_DEFAULT = 0
    const val PRESS_HOLD_MINUTE_DEFAULT = 1
    const val PRESS_HOLD_SECOND_DEFAULT = 0

    const val CODE_RESULT = 1000
    const val ARG_INTENT_RESULT = "intent_result"
    const val INTENT_RESULT = 1
    fun setString(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String, value: String): String {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, value)?:value
    }

    fun setBoolean(context: Context, key: String, value: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getInt(context: Context, key: String, value: Int): Int {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, value)
    }

    fun setInt(context: Context, key: String, value: Int) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getBoolean(context: Context, key: String, value: Boolean): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, value)
    }

    fun getLong(context: Context, key: String, value: Long): Long {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getLong(key, value)
    }

    fun setLong(context: Context, key: String, value: Long) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }
}