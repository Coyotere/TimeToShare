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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class createGroup extends AppCompatActivity {

    private  DatePickerDialog datePickerDialogDepart;
    private  DatePickerDialog datePickerDialogRetour;
    TextView dateDepart,dateRetour,name,mail;
    EditText titreGroupe, zoneMessage;
    GroupAdapter adapter;
    final Context context = this;
    int repetitionChoose;
    Spinner spinner;
    PopupContact popupContact;
    private String[] textAlea;
    ImageButton image;

    List<Contact> items = new ArrayList<Contact>();
    PopupImage popupImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_create_group);

        textAlea = getResources().getStringArray(R.array.textAlea);
        image = findViewById(R.id.groupImage);


        RecyclerView recyclerView = findViewById(R.id.recycleViewCreate);
        popupContact = new PopupContact(getBaseContext(), recyclerView);
        popupImage = new PopupImage(getBaseContext(),recyclerView);

        //Spinner
        repetitionChoose = 3;
        spinner = findViewById(R.id.spinner);

        int[] repet = {1,3,5,7,14};
        String[] repetString = {"1 jour", "3 jours", "5 jours", "1 semaine", "2 semaines"};
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

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupImage.show(view);
            }
        });

        titreGroupe = findViewById(R.id.titreGroupe);
        zoneMessage = findViewById(R.id.zoneMessage);
        zoneMessage.setText(getTextAlea());
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
                if(save()){
                    finish();
                }
            }
        });

        ImageButton refreshText = findViewById(R.id.refreshMessage);
        refreshText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoneMessage.setText(getTextAlea());
            }
        });

        dateDepart = findViewById(R.id.dateDepart);
        dateRetour = findViewById(R.id.dateRetour);

        initDatePicker(dateDepart);
        initDatePicker(dateRetour);

        //name = findViewById(R.id.name);

        adapter = new GroupAdapter(createGroup.this, items,popupContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAddUsersCreate);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupContact.show(view);
                //startActivity(new Intent(createGroup.this, PopupContact.class));
                //items.add(new ItemGroup("Nom", "sEmail"));
                //adapter.notifyDataSetChanged();
            }
        });

        popupContact.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(popupContact.newContact){
                    items.add(new Contact(popupContact.getName(), popupContact.getMail()));
                    adapter.notifyDataSetChanged();
                }
                else if(popupContact.contactModified){
                    items.get(popupContact.numItem).setName(popupContact.getName());
                    items.get(popupContact.numItem).setMail(popupContact.getMail());
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private String getTextAlea(){
        int randomIndex = new Random().nextInt(textAlea.length);
        return textAlea[randomIndex];
    }

    private boolean save() {

        if(!canSave()){
            return false;
        }


        SharedPreferences userData = getSharedPreferences(titreGroupe.getText().toString(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit_userData = userData.edit();

        edit_userData.putInt("numberMembers", items.size());
        String members = "";

        for(Contact contact: items){
            if(!members.equals("")){
                members = members + ";";
            }
            members = members + contact.toString();
        }

        edit_userData.putString("members", members);

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
        edit_userData.putString(titreGroupe.getText().toString(), titreGroupe.getText().toString());
        edit_userData.commit();

        return true;
    }

    private boolean canSave(){
        if(titreGroupe.getText().toString().equals("")){
            Toast.makeText(createGroup.this, getText(R.string.erreurNomGroupe), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(this.dateRetour.getText().toString().equals(getString(R.string.dateRetour)) || this.dateDepart.getText().toString().equals(getString(R.string.dateDepart))){
            Toast.makeText(createGroup.this, getText(R.string.erreurNoDate), Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(items.size() == 0){
            Toast.makeText(createGroup.this, getText(R.string.erreurContact), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(createGroup.this, getText(R.string.erreurDateDepartRetour), Toast.LENGTH_SHORT).show();
                return false;
            }

        }

        return true;
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
        if(view.getId() == R.id.dateDepart){
            datePickerDialogDepart.show();
        }
        else{
            datePickerDialogRetour.show();
        }

    }
}