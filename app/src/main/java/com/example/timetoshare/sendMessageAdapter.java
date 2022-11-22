package com.example.timetoshare;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class sendMessageAdapter extends RecyclerView.Adapter<sendMessageHolder> {

    Context context;
    List<ItemSendMessage> items;

    public sendMessageAdapter(Context context, List<ItemSendMessage> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public sendMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new sendMessageHolder(LayoutInflater.from(context).inflate(R.layout.item_sm, parent, false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull sendMessageHolder holder, int position) {
        holder.imageView.setImageResource(items.get(holder.getAbsoluteAdapterPosition()).getImage());
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
