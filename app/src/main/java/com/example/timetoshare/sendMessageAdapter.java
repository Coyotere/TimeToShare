package com.example.timetoshare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class sendMessageAdapter extends RecyclerView.Adapter<sendMessageHolder> {

    Context context;
    List<ItemSendMessage> items;
    Activity smActivity;

    public sendMessageAdapter(Context context, List<ItemSendMessage> items, Activity smActivity) {
        this.context = context;
        this.items = items;
        this.smActivity = smActivity;
    }

    @NonNull
    @Override
    public sendMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new sendMessageHolder(LayoutInflater.from(context).inflate(R.layout.item_sm, parent, false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull sendMessageHolder holder, int position) {
        Glide.with(this.smActivity) //SHOWING PREVIEW OF IMAGE
                .load(this.items.get(holder.getAbsoluteAdapterPosition()).getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.imageView);
        //holder.imageView.setImageResource(items.get(holder.getAbsoluteAdapterPosition()).getImage());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(holder.getAbsoluteAdapterPosition());
                notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
