package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class sendingMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_message);

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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //leave
            }
        });

    }

    public String getTitleSM() {
        String title = "";
        title = "Test title";
        return title;
    }

    public int getImageSM() {
        int img = 0;
        img = R.drawable.dragon;
        return img;
    }

    public String getTextSM() {
        String txt = "";
        txt = "Test message";
        return txt;
    }

    public void setRandomImagesSM() {

    }

    public void requestSendSM() {

    }

    public void accessPicturesSM() {

    }
}