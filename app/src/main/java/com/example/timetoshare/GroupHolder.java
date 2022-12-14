package com.example.timetoshare;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupHolder extends RecyclerView.ViewHolder {

    TextView name, mail;
    Button btnSupp;


    public GroupHolder(@NonNull View itemView, PopupContact popupContact) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        mail = itemView.findViewById(R.id.mail);
        btnSupp = itemView.findViewById(R.id.deleteContact);
    }
}
