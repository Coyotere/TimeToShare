package com.example.timetoshare;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {

    Context context;
    List<ItemMainActivity> items;

    public MainAdapter(Context context, List<ItemMainActivity> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(context).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        int numMember = items.get(position).getNumMember();
        String member = String.format(context.getResources().getQuantityString(R.plurals.member,numMember,numMember ));
        holder.memberView.setText(member);

        holder.nameView.setText(items.get(position).name);
        holder.imageView.setImageResource(items.get(position).image);


       switch(items.get(position).active){
           case -1:
               holder.activeView.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
               break;
           case 0:
               holder.activeView.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
               break;
           case 1:
               holder.activeView.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
       }
        holder.bellView.setImageResource(R.drawable.bell);
        holder.bellView.setVisibility(items.get(position).needMessage ? View.VISIBLE:View.INVISIBLE);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
