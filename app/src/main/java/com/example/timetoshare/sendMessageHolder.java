package com.example.timetoshare;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class sendMessageHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    Button deleteButton;

    public sendMessageHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        deleteButton = itemView.findViewById(R.id.deleteButton);
    }
}
