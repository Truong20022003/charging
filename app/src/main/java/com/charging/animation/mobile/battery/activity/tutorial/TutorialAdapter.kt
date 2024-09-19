package com.charging.animation.mobile.battery.activity.tutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.activity.tutorial.TutorialAdapter.TutorialAdapterHolder
import com.charging.animation.mobile.battery.model.TutorialModel

class TutorialAdapter(private val list: MutableList<TutorialModel>,var context: Context) : RecyclerView.Adapter<TutorialAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialAdapterHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tutorial, parent, false)
        return TutorialAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: TutorialAdapterHolder, position: Int) {
       try {
           if(position == 0 || position == 1){
               Glide.with(context).asGif().load(list[position].image).into(holder.imgGuide)
           }else{
               Glide.with(context).load(list[position].image).into(holder.imgGuide)
           }
       }catch (e : Exception){
           e.printStackTrace()
       }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TutorialAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgGuide: ImageView

        init {
            imgGuide = itemView.findViewById(R.id.img_guide)
        }
    }
}