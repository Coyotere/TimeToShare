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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class manageGroup extends AppCompatActivity {

    private DatePickerDialog datePickerDialogDepart;
    private  DatePickerDialog datePickerDialogRetour;
    TextView dateDepart,dateRetour;
    EditText zoneMessage;
    ImageView img;
    EditText titre;
    ImageButton supUser;
    SharedPreferences db;
    GroupAdapter adapter;
    int repetitionChoose;
    String initialTitle;
    Button valider;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);
        db = getSharedPreferences(getIntent().getExtras().getString("groupName"), Context.MODE_PRIVATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");


        spinner = findViewById(R.id.spinner);

        int[] repet = {3,5,7,14};
        String[] repetString = {"3 jours", "5 jours", "1 semaine", "2 semaines"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,repetString );
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    repetitionChoose = repet[position];
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        zoneMessage = findViewById(R.id.zoneMessage);
        zoneMessage.setText(getTextSM());

        final ImageButton leave = (ImageButton) findViewById(R.id.leaveManager);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img = findViewById(R.id.imageGroupe);
        img.setImageResource(getImageSM());

        titre = findViewById(R.id.titreGroupe);
        titre.setText(getTitleSM());
        initialTitle = getTitleSM();

        repetitionChoose = db.getInt("repetition", 0);

        int pos = -1;
        for(int i = 0; i < repet.length; i++) {
            if(repet[i] == repetitionChoose) {
                pos = i;
                break;
            }
        }
        spinner.setSelection(pos);


        Date startingDate = null;
        try {
            startingDate = format.parse(db.getString("startingDate", null));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date finalDate = null;
        try {
            finalDate = format.parse(db.getString("finalDate", null));
        } catch (ParseException e) {
            e.printStackTrace();
        }




        RecyclerView recyclerView = findViewById(R.id.recycleView);

        List<ItemGroup> items = new ArrayList<ItemGroup>();

        items.add(new ItemGroup("Lucie Ditée", "lucide@gmail.com"));
        items.add(new ItemGroup("Alain Térieur", "dedans@orange.fr"));

        adapter = new GroupAdapter(manageGroup.this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAddUsers);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
                items.add(new ItemGroup("Nom", "Mail"));
            }
        });

        Button save = (Button) findViewById(R.id.SaveManage);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(save()) {
                    finish();
                }
            }
        });


        //supUser = (ImageButton) findViewById(R.id.btnSupp);
        //supUser.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View view) {
            //    adapter.notifyDataSetChanged();
             //   items.remove(items.size()-1);
           // }
        //});

        dateDepart = findViewById(R.id.dateDepartManage);

        dateRetour = findViewById(R.id.dateRetourManage);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startingDate);
        dateDepart.setText(makeDateString(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)));

        cal.setTime(finalDate);
        dateRetour.setText(makeDateString(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)));



        initDatePicker(dateDepart);

        initDatePicker(dateRetour);

    }

    private boolean save() {


        if(!canSave()){
            return false;
        }

        SharedPreferences userData = getSharedPreferences(initialTitle, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_userData = userData.edit();
        edit_userData.clear();

        userData = getSharedPreferences(titre.getText().toString(), Context.MODE_PRIVATE);
        edit_userData = userData.edit();


        edit_userData.putInt("numberMembers", 2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String[] dateText = dateDepart.getText().toString().split(" ");

        dateText[1] = String.valueOf(getNumMonth(dateText[1]));

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(dateText[2]), Integer.parseInt(dateText[1]), Integer.parseInt(dateText[0]));
        Date date = cal.getTime();
        String dateTime = dateFormat.format(date);
        edit_userData.putString("startingDate", dateTime);
        edit_userData.putString("lastRepetition", dateTime);



        dateText = dateRetour.getText().toString().split(" ");
        dateText[1] = String.valueOf(getNumMonth(dateText[1]));


        cal.set(Integer.parseInt(dateText[2]), Integer.parseInt(dateText[1]), Integer.parseInt(dateText[0]));

        date = cal.getTime();

        dateTime = dateFormat.format(date);
        edit_userData.putString("finalDate", dateTime);

        edit_userData.putInt("repetition", repetitionChoose);

        edit_userData.putInt("image", R.drawable.chicken);

        edit_userData.putString("message", zoneMessage.getText().toString());

        edit_userData.commit();





        userData = getSharedPreferences("groupsName", Context.MODE_PRIVATE);
        edit_userData = userData.edit();

        edit_userData.remove(initialTitle);

        edit_userData.putString(titre.getText().toString(), titre.getText().toString());
        edit_userData.commit();

        return true;
    }

    private boolean canSave(){
        if(titre.getText().toString().equals("")){
            Toast.makeText(manageGroup.this, getText(R.string.erreurNomGroupe), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(this.dateRetour.getText().toString().equals(getString(R.string.dateRetour)) || this.dateDepart.getText().toString().equals(getString(R.string.dateDepart))){
            Toast.makeText(manageGroup.this, getText(R.string.erreurNoDate), Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            String[] dateDepartArray = this.dateDepart.getText().toString().split(" ");
            String[] dateRetourArray = this.dateRetour.getText().toString().split(" ");

            dateDepartArray[1] = String.valueOf(getNumMonth(dateDepartArray[1]));
            dateRetourArray[1] = String.valueOf(getNumMonth(dateRetourArray[1]));

            Calendar calDepart = Calendar.getInstance();
            calDepart.set(Integer.parseInt(dateDepartArray[2]), Integer.parseInt(dateDepartArray[1]), Integer.parseInt(dateDepartArray[0]));
            Date dateDepart = calDepart.getTime();

            Calendar calRetour = Calendar.getInstance();
            calRetour.set(Integer.parseInt(dateRetourArray[2]), Integer.parseInt(dateRetourArray[1]), Integer.parseInt(dateRetourArray[0]));
            Date dateRetour = calRetour.getTime();

            if(dateRetour.before(dateDepart) || dateRetour.equals(dateDepart)){
                Toast.makeText(manageGroup.this, getText(R.string.erreurDateDepartRetour), Toast.LENGTH_SHORT).show();
                return false;
            }

        }

        return true;
    }

    private String getTextSM() {
        return db.getString("message", null);
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
        return day + " " +  getMonthFormat(month) + " " + year;
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
        if(view.getId() == R.id.dateDepartManage){
            datePickerDialogDepart.show();
        }
        else{
            datePickerDialogRetour.show();
        }

    }
}