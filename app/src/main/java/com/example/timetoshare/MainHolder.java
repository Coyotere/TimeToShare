package com.example.timetoshare;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainHolder extends RecyclerView.ViewHolder {

    ImageView imageView, bellView, activeView;
    TextView nameView, memberView;
    Context context;

    public MainHolder(@NonNull View itemView, Context _context) {
        super(itemView);
        context = _context;
        imageView = itemView.findViewById(R.id.image_itemMain);
        bellView = itemView.findViewById(R.id.bell_itemMain);
        nameView = itemView.findViewById(R.id.name_itemMain);
        activeView = itemView.findViewById(R.id.active_itemMain);
        memberView = itemView.findViewById(R.id.member_itemMain);

        bellView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("Click sur Bell");
                Intent myIntent = new Intent(context, sendingMessage.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("groupName", nameView.getText().toString());
                context.startActivity(myIntent);
            }
        });

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println("Click sur Bell");
                Intent myIntent = new Intent(context, manageGroup.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("groupName", nameView.getText().toString());
                context.startActivity(myIntent);
            }
        });
    }
}
