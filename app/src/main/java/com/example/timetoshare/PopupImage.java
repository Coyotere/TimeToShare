package com.example.timetoshare;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;

public class PopupImage extends PopupWindow {

    Context context;
    private View view;
    int idImage;

    ImageButton chicken,girafe,lion,dog,dragon,tiger;

    public PopupImage(Context ctx, View v) {
        super(ctx);
        context = ctx;
        view = v;
        setContentView(LayoutInflater.from(context).inflate(R.layout.activity_popup_group_icons, null));

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        View popupView = getContentView();
        setFocusable(true);

        chicken = popupView.findViewById(R.id.chicken);
        girafe = popupView.findViewById(R.id.girafe);
        lion = popupView.findViewById(R.id.lion);
        dog = popupView.findViewById(R.id.dog);
        dragon  = popupView.findViewById(R.id.dragon);
        tiger = popupView.findViewById(R.id.tiger);


        setButton(chicken, R.drawable.chicken);
        setButton(girafe, R.drawable.girafe);
        setButton(lion, R.drawable.lion);
        setButton(dog, R.drawable.dog);
        setButton(dragon, R.drawable.dragon);
        setButton(tiger,R.drawable.tiger);

    }

    public void show(View v) {
        //name.setText("");
        //mail.setText("");
        this.idImage = -1;
        showAtLocation(view, Gravity.CENTER, 0, 0);
        //newContact = false;
        //modifiContact = false;
        //contactModified = false;
    }

    private void setButton(ImageButton button, int id){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idImage = id;
                dismiss();
            }
        });

    }
}
