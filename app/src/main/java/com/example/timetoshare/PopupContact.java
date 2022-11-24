package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

public class PopupContact extends PopupWindow {

    Context context;
    EditText name,mail;
    Button save;
    public Boolean newContact;

    public PopupContact(Context ctx) {
        super(ctx);
        context = ctx;
        setContentView(LayoutInflater.from(context).inflate(R.layout.activity_popup_contact, null));

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        View popupView = getContentView();
        setFocusable(true);

        save = popupView.findViewById(R.id.dialogButtonOK);
        name = popupView.findViewById(R.id.name);
        mail = popupView.findViewById(R.id.mail);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getMail().equals("") && !getName().equals("")){
                    newContact = true;
                    dismiss();
                }
                else{
                    Toast.makeText(context, "Tout les champs doivent être complété", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void show(View v) {
        name.setText("");
        mail.setText("");
        showAtLocation(v, Gravity.CENTER, 0, 0);
        newContact = false;

    }
    public void show(View v, String name, String mail){
        this.name.setText(name);
        this.mail.setText(mail);
        showAtLocation(v, Gravity.CENTER, 0, 0);
        newContact = false;
    }

    public String getName(){
        return name.getText().toString();
    }

    public String getMail(){
        return mail.getText().toString();
    }
}