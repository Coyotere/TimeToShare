package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class sendingMessage extends AppCompatActivity {

    SharedPreferences db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_message);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSM);

        List<ItemSendMessage> pictures = new ArrayList<ItemSendMessage>();
        pictures.add(new ItemSendMessage(R.drawable.dog));
        pictures.add(new ItemSendMessage(R.drawable.dragon));
        pictures.add(new ItemSendMessage(R.drawable.chicken));
        pictures.add(new ItemSendMessage(R.drawable.dog));
        pictures.add(new ItemSendMessage(R.drawable.dragon));
        pictures.add(new ItemSendMessage(R.drawable.chicken));
        pictures.add(new ItemSendMessage(R.drawable.dog));
        pictures.add(new ItemSendMessage(R.drawable.dragon));
        pictures.add(new ItemSendMessage(R.drawable.chicken));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new sendMessageAdapter(getApplicationContext(), pictures));

        db = getSharedPreferences(getIntent().getExtras().getString("groupName"), Context.MODE_PRIVATE);

        final TextView nMembersSM = (TextView) findViewById(R.id.nMembersSM);
        nMembersSM.setText(getMembersSM() + " membres");

        final TextView titleSM = (TextView) findViewById(R.id.titleSM);
        titleSM.setText(getTitleSM());

        final ImageView imgSM = (ImageView) findViewById(R.id.iconeSM);
        imgSM.setImageResource(getImageSM());

        final TextView textSM = (TextView) findViewById(R.id.textSM);
        textSM.setText(getTextSM());

        final Button addButton = (Button) findViewById(R.id.addSM);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessPicturesSM();
            }
        });

        final Button sendButton = (Button) findViewById(R.id.sendSM);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSendSM();
            }
        });

        final ImageButton leaveButton = (ImageButton) findViewById(R.id.leaveSM);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public int getMembersSM() {
        return db.getInt("numberMembers", -1);
    }
    public String getTitleSM() {
        return getIntent().getExtras().getString("groupName");
    }

    public int getImageSM() {
        return db.getInt("image", 0);
    }

    public String getTextSM() {
        return db.getString("message", "Error");
    }

    public void setRandomImagesSM() {

    }

    public void requestSendSM() {

    }

    public void accessPicturesSM() {

    }
}