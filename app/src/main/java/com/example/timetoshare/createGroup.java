package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class createGroup extends AppCompatActivity {

    private  DatePickerDialog datePickerDialogDepart;
    private  DatePickerDialog datePickerDialogRetour;
    TextView dateDepart;
    TextView dateRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);


        Button btnAnnuler = findViewById(R.id.annuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnValider = findViewById(R.id.valider);
        btnValider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dateDepart = findViewById(R.id.dateDepart);
        dateRetour = findViewById(R.id.dateRetour);

        initDatePicker(dateDepart);
        initDatePicker(dateRetour);

    }

    private void initDatePicker(TextView dateView) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateView.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        if(dateView == dateDepart){
            datePickerDialogDepart = new DatePickerDialog(this,style, dateSetListener, year, month, day);
        }
        else{
            datePickerDialogRetour = new DatePickerDialog(this,style, dateSetListener, year, month, day);

        }
    }

    private String makeDateString(int day, int month, int year) {
        return day + "." +  getMonthFormat(month) + "." + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1:
                return "Janvier";
            case 2:
                return "Février";
            case 3:
                return "Mars";
            case 4:
                return "Avril";
            case 5:
                return "Mai";
            case 6:
                return "Juin";
            case 7:
                return "Juillet";
            case 8:
                return "Aout";
            case 9:
                return "Septembre";
            case 10:
                return "Octobre";
            case 11:
                return "Novembre";
            default:
                return "Décembre";
        }
    }


    public void openDatePicker(View view) {
        if(view.getId() == R.id.dateDepart){
            datePickerDialogDepart.show();
        }
        else{
            datePickerDialogRetour.show();
        }

    }
}