package com.charging.animation.mobile.battery.fragment.home.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.api.CommonAds
import com.charging.animation.mobile.battery.api.Results
import com.charging.animation.mobile.battery.listener.Listener
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import pl.droidsonroids.gif.GifImageView

@Suppress("DEPRECATION")
class AnimContentAndAdsAdapter(
    list: List<Results>,
    private val context: Context,
    isShowNative: Boolean,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData: MutableList<Results> = mutableListOf()
    private var listPos: MutableList<Int> = mutableListOf()

    init {
        if (isShowNative) {
            listData.addAll(getListWithAds(list))
        } else {
            listData.addAll(list)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadFailNativeAd() {
        listData = listData.filterNot { it.id == -1 }.toMutableList()
        listPos.clear()
        notifyDataSetChanged()
    }

    private fun getListWithAds(list: List<Results>): List<Results> {
        val listTmp: MutableList<Results> = mutableListOf()
        for (i in list.indices) {
            if (i != 0 && i % 2 == 0) {
                listTmp.add(Results(-1, "", -1, ""))
                listPos.add(listTmp.size - 1)
            }
            listTmp.add(list[i])
        }
        return listTmp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_content_anim, parent, false)
        return if (viewType == -11001) AdsViewHolder(view) else MobileViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MobileViewHolder -> bindContent(holder, position)
            is AdsViewHolder -> {
                // Bind ads view here if necessary
            }
        }
    }

    private fun bindContent(holder: MobileViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            CommonAds.logEvent(
                context,
                "home_item_choose",
                "item_id",
                listData[position].id.toString()
            )
            listener.onClick(listData[position])
        }

        try {
            Glide.with(context)
                .load(R.drawable.home_img_loading)
                .into(holder.down)
        }catch (e : Exception){
            e.printStackTrace()
        }


        try {
            Glide.with(context)
                .asGif()
                .load(listData[position].link)
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

            holder.ads.visibility = if (listData[position].free) View.GONE else View.VISIBLE
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        holder.blur(context)
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData[position].id == -1) -11001 else super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(listAll: List<Results>, isShowNative: Boolean) {
        listData.clear()
        listPos.clear()
        if (isShowNative) {
            listData.addAll(getListWithAds(listAll))
        } else {
            listData.addAll(listAll)
        }
        notifyDataSetChanged()
    }

    class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class MobileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gif: ImageView = itemView.findViewById(R.id.imgView)
        var ads: ImageView = itemView.findViewById(R.id.adsView)
        var down: ImageView = itemView.findViewById(R.id.imgDown)
        val blur: BlurView = itemView.findViewById(R.id.blurViewCreator)

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
