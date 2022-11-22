package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class createGroup extends AppCompatActivity {

    private  DatePickerDialog datePickerDialogDepart;
    private  DatePickerDialog datePickerDialogRetour;
    TextView dateDepart,dateRetour;
    EditText titreGroupe;
    GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        titreGroupe = findViewById(R.id.titreGroupe);

        Button btnAnnuler = findViewById(R.id.annuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                save();
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

        RecyclerView recyclerView = findViewById(R.id.recycleViewCreate);

        List<ItemGroup> items = new ArrayList<ItemGroup>();

        adapter = new GroupAdapter(createGroup.this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAddUsersCreate);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
                items.add(new ItemGroup("Nom", "Email"));
            }
        });

    }

    private void save() {

        SharedPreferences userData = getSharedPreferences(titreGroupe.getText().toString(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_userData = userData.edit();

        edit_userData.putInt("numberMembers", 2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String[] dateText = dateDepart.getText().toString().split(".");

        dateText[1] = String.valueOf(getNumMonth(dateText[1]));

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(dateText[2]), Integer.parseInt(dateText[1]), Integer.parseInt(dateText[0]));
        Date date = cal.getTime();
        String dateTime = dateFormat.format(date);
        edit_userData.putString("startingDate", dateTime);
        edit_userData.putString("lastRepetition", dateTime);

        dateText = dateRetour.getText().toString().split(".");

        dateText[1] = String.valueOf(getNumMonth(dateText[1]));

        cal.set(Integer.parseInt(dateText[2]), Integer.parseInt(dateText[1]), Integer.parseInt(dateText[0]));

        date = cal.getTime();

        dateTime = dateFormat.format(date);
        edit_userData.putString("finalDate", dateTime);

        edit_userData.putInt("repetition", 3);

        edit_userData.putInt("image", R.drawable.chicken);
        edit_userData.commit();

        userData = getSharedPreferences("groupsName", Context.MODE_PRIVATE);
        edit_userData = userData.edit();
        edit_userData.putString(titreGroupe.getText().toString(), titreGroupe.getText().toString());
        edit_userData.commit();

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

    private int getNumMonth(String month) {
        switch (month) {
            case "Janvier":
                return 0;
            case "Février":
                return 1;
            case "Mars":
                return 2;
            case "Avril":
                return 3;
            case  "Mai":
                return 4;
            case "Juin":
                return 5;
            case "Juillet":
                return 6;
            case "Aout":
                return 7;
            case "Septembre":
                return 8;
            case "Octobre":
                return 9;
            case "Novembre":
                return 10;
            default:
                return 11;
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