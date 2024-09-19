package com.charging.animation.mobile.battery.fragment.alarm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.listener.Listener;
import com.charging.animation.mobile.battery.model.Ringtone;

import java.util.List;

public class RingtoneAdapter  extends RecyclerView.Adapter<RingtoneAdapter.MobileViewHolder>{
    List<Ringtone> list;
    Context context;
    Listener listener;

    public RingtoneAdapter(List<Ringtone> list, Context context, Listener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RingtoneAdapter.MobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RingtoneAdapter.MobileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ringone, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RingtoneAdapter.MobileViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitle.setText(list.get(position).name);
        holder.tvTime.setText(list.get(position).time);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String path = RealPathUtil.getRealPath(context, );
                setSelectRingtone(list.get(position).name);
                listener.onClick(list.get(position));
            }
        });

        holder.v2.setVisibility(View.GONE);
        if (list.get(position).check) {
            holder.v2.setVisibility(View.VISIBLE);
            holder.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white));
            holder.tvTime.setTextColor(ContextCompat.getColor(context,R.color.white));
        } else {
            holder.v2.setVisibility(View.GONE);
            holder.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.color_8E8E8E) );
            holder.tvTime.setTextColor(ContextCompat.getColor(context,R.color.color_8E8E8E));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MobileViewHolder extends RecyclerView.ViewHolder{
        TextView  tvTitle;
        TextView  tvTime;
        ImageView v2;
        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            v2 = itemView.findViewById(R.id.tvName);

            tvTitle.setSelected(true);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectRingtone(String name) {
        for (Ringtone data : list) {
            data.check = data.name.equals(name);
        }
        notifyDataSetChanged();
    }
}
