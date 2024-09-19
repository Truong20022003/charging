package com.charging.animation.mobile.battery.activity.apply.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.activity.apply.model.Setting;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.CountryHolder>{


    Context context;
    List<Setting> list;
    SelectCountry selectCountry;
    public SettingAdapter(Context context, List<Setting> list, SelectCountry selectCountry){
        this.context = context;
        this.list = list;
        this.selectCountry = selectCountry;
    }

    public interface SelectCountry{
        void onClick(int select);
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(LayoutInflater.from(context).inflate(R.layout.item_country,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bin(list.get(position));
        holder.itemView.setOnClickListener(v -> selectCountry.onClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CountryHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView imgDone;

        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgDone= itemView.findViewById(R.id.imgDone);
        }

        public void bin(Setting setting){
            tvTitle.setText(setting.title);
            if(setting.check){
                itemView.setBackgroundResource(R.drawable.dialog_setting_item);
                imgDone.setVisibility(View.VISIBLE);
            }else {
                itemView.setBackgroundResource(android.R.color.transparent);
                imgDone.setVisibility(View.GONE);
            }
        }
    }
}
