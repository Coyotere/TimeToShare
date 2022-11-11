package com.example.timetoshare;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainHolder extends RecyclerView.ViewHolder {

    ImageView imageView, bellView, activeView;
    TextView nameView, memberView;

    public MainHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_itemMain);
        bellView = itemView.findViewById(R.id.bell_itemMain);
        nameView = itemView.findViewById(R.id.name_itemMain);
        activeView = itemView.findViewById(R.id.active_itemMain);
        memberView = itemView.findViewById(R.id.member_itemMain);

    }
}
