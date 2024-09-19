package com.charging.animation.mobile.battery.activity.language

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.charging.animation.mobile.battery.activity.language.model.LanguageModel
import com.bumptech.glide.Glide
import com.charging.animation.mobile.battery.R

class LanguageStartAdapter  (private val context: Context,
                             private var list: List<LanguageModel>,
                             private val listener: Listener
) : RecyclerView.Adapter<LanguageStartAdapter.CountryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(LayoutInflater.from(context).inflate(R.layout.item_language, parent, false))
    }

    fun updateData(newList: List<LanguageModel>) {
        list = newList
        notifyDataSetChanged()
    }

    fun setSelectedLanguage(selectedLanguage: LanguageModel) {
        for (data in list) {
            data.active = data == selectedLanguage
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val data = list[position]
        holder.bind(data, context)

        Glide.with(context).load(data.image).into(holder.ivAvatar)

        holder.itemView.setOnClickListener {
            listener.onClickLanguage(data)
        }

        if (data.active) {
            holder.v2.setBackgroundResource(R.drawable.language_bg_item)
            holder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.v2.setBackgroundResource(R.drawable.language_bg_item_off)
            holder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_8E8E8E))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.findViewById(R.id.imgIcon)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val v2: View = itemView.findViewById(R.id.rootLanguage)

        fun bind(serverModel: LanguageModel, context: Context) {
            tvTitle.text = serverModel.languageName
            if (serverModel.active) {
                v2.setBackgroundResource(R.drawable.language_bg_item)
                tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                v2.setBackgroundResource(R.drawable.language_bg_item_off)
                tvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_8E8E8E))
            }
        }
    }

    open class Listener {
        open fun onClickLanguage(languageSelect: LanguageModel) {}
    }
}