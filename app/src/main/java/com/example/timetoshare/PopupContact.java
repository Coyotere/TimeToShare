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
    private Boolean modifiContact;
    public Boolean contactModified;
    public int numItem;
    private View view;

    public PopupContact(Context ctx, View v) {
        super(ctx);
        context = ctx;
        view = v;
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
                if(getMail().equals("") && getName().equals("")){
                    Toast.makeText(context, "Tout les champs doivent être complétés", Toast.LENGTH_SHORT).show();

                }
                else if(numIteration(getMail()) != 1){
                    Toast.makeText(context, "Veuillez rentré une adresse valide", Toast.LENGTH_SHORT).show();
                }
                else if(getMail().indexOf(",") > 0 || getMail().indexOf(";") > 0 || getName().indexOf(",") > 0 || getName().indexOf(";") > 0){
                    Toast.makeText(context, "Impossible d'ajouter les symboles \' ; \' et \' , \'", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!modifiContact) {
                        newContact = true;
                    }
                    else {
                        contactModified = true;
                    }
                    dismiss();
                }
            }
        });
    }

    private int numIteration(String mail){
        int num = 0;
        for(int index = mail.indexOf("@"); index >= 1; index = mail.indexOf("@",index + 1)){
            num ++;
        }
        return num;
    }

    public void show(View v) {
        name.setText("");
        mail.setText("");
        showAtLocation(view, Gravity.CENTER, 0, 0);
        newContact = false;
        modifiContact = false;
        contactModified = false;
    }

    public void show(View v, String name, String mail, int numItem){
        this.name.setText(name);
        this.mail.setText(mail);
        showAtLocation(view, Gravity.CENTER, 0, 0);
        newContact = false;
        modifiContact = true;
        this.numItem = numItem;
        contactModified = false;
    }

    public String getName(){
        return name.getText().toString();
    }

    public String getMail(){
        return mail.getText().toString();
    }
}