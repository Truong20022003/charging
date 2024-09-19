package com.charging.animation.mobile.battery.custom.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.activity.internet.NoInternetActivity
import com.charging.animation.mobile.battery.custom.listner.TapListener
import com.charging.animation.mobile.battery.util.CheckInternet


fun View.tap(action: (view: View?) -> Unit) {
    setOnClickListener(object : TapListener() {
        override fun onTap(v: View?) {
            if (!CheckInternet.haveNetworkConnection(context)) {
                context.findActivity()?.let {
                    val intent = Intent(it, NoInternetActivity::class.java)
                    it.startActivity(intent)
                    it.overridePendingTransition(0, 0)
                }
            } else {
                action(v)
            }
        }
    })
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

fun ImageView.loadImage(context: Context,data:Int, imageView: ImageView, isGif:Boolean = false){
    try {
        if(isGif){
            Glide.with(context).asGif().load(data).into(imageView)
        }else{
            Glide.with(context).load(data).into(imageView)
        }

    }catch (e : Exception){
        e.printStackTrace()
    }
}