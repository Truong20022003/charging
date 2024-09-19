package com.charging.animation.mobile.battery.fragment.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ViewTarget;
import com.charging.animation.mobile.battery.R;
import com.charging.animation.mobile.battery.api.Results;
import com.charging.animation.mobile.battery.listener.Listener;


import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class AnimContentAdapter extends RecyclerView.Adapter<AnimContentAdapter.MobileViewHolder> {
    List<Results> list;
    Context context;
    Listener listener;

    public AnimContentAdapter(List<Results> list, Context context, Listener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimContentAdapter.MobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimContentAdapter.MobileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_content_anim, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimContentAdapter.MobileViewHolder holder, @SuppressLint("RecyclerView") int position) {
        bindContent(holder, position);
    }

    private void bindContent(@NonNull MobileViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(position));
            }
        });

        try{
            //holder.itemView.setOnClickListener(v -> listener.onClickItem(list.get(position),position));
            ViewTarget<GifImageView, GifDrawable> target = Glide.with(context)
                    .asGif()
                    .load(list.get(position).link)
                    .into(new ViewTarget<GifImageView, GifDrawable>((GifImageView) holder.gif) {
                        @Override
                        public void onResourceReady(@NonNull GifDrawable resource, com.bumptech.glide.request.transition.Transition<? super GifDrawable> transition) {

                            try{
                                this.view.setImageDrawable(resource);
                                int totalFrames = resource.getFrameCount();
                                if (totalFrames >= 2) {
                                    resource.setLevel(resource.getFrameCount() / 2);
                                }else {
                                    resource.setLevel(resource.getFrameCount());
                                }


                            }catch (Exception exception){
                                exception.printStackTrace();
                            }
                        }
                    });
            if (list.get(position).free) {
                holder.ads.setVisibility(View.GONE);
            }else {
                holder.ads.setVisibility(View.VISIBLE);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MobileViewHolder extends RecyclerView.ViewHolder {

        ImageView gif, ads, down;

        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);
            gif = itemView.findViewById(R.id.imgView);
            ads = itemView.findViewById(R.id.adsView);
            down = itemView.findViewById(R.id.imgDown);
        }
    }
}
