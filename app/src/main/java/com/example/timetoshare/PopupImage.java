package com.example.timetoshare;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class PopupImage extends PopupWindow {

    Context context;
    private View view;

    public PopupImage(Context ctx, View v) {
        super(ctx);
        context = ctx;
        view = v;
        setContentView(LayoutInflater.from(context).inflate(R.layout.activity_popup_group_icons, null));

        //setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        View popupView = getContentView();
        setFocusable(true);
    }

    public void show(View v) {
        //name.setText("");
        //mail.setText("");
        showAtLocation(view, Gravity.CENTER, 0, 0);
        //newContact = false;
        //modifiContact = false;
        //contactModified = false;
    }
}
