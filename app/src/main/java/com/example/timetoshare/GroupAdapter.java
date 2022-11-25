package com.example.timetoshare;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {

    Context context;
    List<Contact> items;
    PopupContact popupContact;
    public GroupAdapter(Context context, List<Contact> items, PopupContact popupContact) {
        this.context = context;
        this.items = items;
        this.popupContact = popupContact;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupHolder (LayoutInflater.from(context).inflate(R.layout.item_group_view,parent,false), popupContact);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.mail.setText(items.get(position).getMail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupContact.show(holder.itemView, items.get(holder.getAbsoluteAdapterPosition()).getName(),items.get(holder.getAbsoluteAdapterPosition()).getMail(),holder.getAbsoluteAdapterPosition());
            }
        });

        holder.btnSupp.setOnClickListener(new View.OnClickListener() {
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
