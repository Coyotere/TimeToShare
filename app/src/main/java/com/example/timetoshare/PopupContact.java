package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class PopupContact extends PopupWindow {

    Context context;

    public PopupContact(Context ctx) {
        super(ctx);
        context = ctx;

        setContentView(LayoutInflater.from(context).inflate(R.layout.activity_popup_contact, null));
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        View popupView = getContentView();
        setFocusable(true);

    }

    public void show(View v) {
        showAtLocation(v, Gravity.CENTER, 0, 0);
    }
}