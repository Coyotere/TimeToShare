package com.example.timetoshare;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopupConfirm extends PopupWindow {

    Context context;
    View view;
    Boolean delete;
    Button confirmTrue, confirmFalse;

    public PopupConfirm(Context ctx, View v) {
        super(ctx);
        context = ctx;
        view = v;
        setContentView(LayoutInflater.from(context).inflate(R.layout.popup_confirm_delete, null));

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        View popupView = getContentView();
        setFocusable(true);

        confirmTrue = popupView.findViewById(R.id.ConfirmTrue);
        confirmFalse = popupView.findViewById(R.id.ConfirmFalse);

        confirmTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete = true;
                dismiss();
            }
        });

        confirmFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete = false;
                dismiss();
            }
        });

    }

    public void show(View v) {
        delete = false;
        showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}
