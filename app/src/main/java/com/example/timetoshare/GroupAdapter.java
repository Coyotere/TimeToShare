package com.example.timetoshare;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {

    Context context;
    List<ItemGroup> items;

    public GroupAdapter(Context context, List<ItemGroup> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupHolder (LayoutInflater.from(context).inflate(R.layout.item_group_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.mail.setText(items.get(position).getMail());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
