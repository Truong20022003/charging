package com.charging.animation.mobile.battery.activity.info.fragment.mobile

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charging.animation.mobile.battery.R
import com.charging.animation.mobile.battery.model.Mobile

class MobileAdapter(var list: List<Mobile>, var context: Context) :
    RecyclerView.Adapter<MobileAdapter.MobileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        return MobileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mobile, parent, false))
    }

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        holder.tvName.text = list[position].name
        holder.tvTitle.text = list[position].title
        textGradient(holder.tvName)
    }

    private fun textGradient(textView: TextView) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, textView.textSize, intArrayOf(
                Color.parseColor("#F16CFF"),
                Color.parseColor("#12D6F0"),
            ), null, Shader.TileMode.CLAMP
        )
        textView.paint.shader = textShader
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MobileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvName = itemView.findViewById(R.id.tvName)
        }
    }
}