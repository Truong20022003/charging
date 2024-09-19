package com.charging.animation.mobile.battery.fragment.gallery.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.listener.Listener
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import pl.droidsonroids.gif.GifImageView
@Suppress("DEPRECATION")

class GalleryAdapter(var list: MutableList<Results>, var context: Context, var listener: Listener) :
    RecyclerView.Adapter<GalleryAdapter.MobileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.item_content_anim, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MobileViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.setOnClickListener {
            listener.onClickItem(list[position], position) }
        try {
            if (list[position].link.contains(".gif") || list[position].link.contains(".GIF")) {
                Glide.with(context)
                    .asGif()
                    .load(list[position].link)
                    .placeholder(R.drawable.splash_img_bg)
                    .into(object : ViewTarget<GifImageView, GifDrawable>(holder.gif as GifImageView) {
                        override fun onResourceReady(
                            resource: GifDrawable,
                            transition: Transition<in GifDrawable>?
                        ) {
                            try {
                                view?.setImageDrawable(resource)
                                resource.level =
                                    if (resource.frameCount >= 2) resource.frameCount / 2 else resource.frameCount
                            } catch (exception: Exception) {
                                exception.printStackTrace()
                            }
                        }
                    })
            } else {
                Glide.with(context)
                    .load(list[position].link)
                    .into(holder.gif)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        holder.ads.visibility = View.GONE
        holder.blur(context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MobileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gif: ImageView
        var ads: ImageView
        var down: ImageView
        val blur: BlurView

        init {
            gif = itemView.findViewById(R.id.imgView)
            ads = itemView.findViewById(R.id.adsView)
            down = itemView.findViewById(R.id.imgDown)
            blur = itemView.findViewById(R.id.blurViewCreator)
        }

        private fun getBlurAlgorithm(context: Context): BlurAlgorithm {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                RenderEffectBlur()
            } else {
                RenderScriptBlur(context)
            }
        }

        fun blur(context: Context) {
            try {
                val activity = context as Activity
                val windowBackground: Drawable = activity.window.decorView.background
                blur.setupWith(itemView as ViewGroup, getBlurAlgorithm(context))
                    .setFrameClearDrawable(windowBackground)
                    .setBlurRadius(25F)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}