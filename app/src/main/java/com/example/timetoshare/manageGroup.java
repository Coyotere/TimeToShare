package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class manageGroup extends AppCompatActivity {

    ImageView img;
    TextView titre, date, zoneMessage;
    ImageButton supUser;
    SharedPreferences db;
    GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);

        db = getSharedPreferences(getIntent().getExtras().getString("groupName"), Context.MODE_PRIVATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");


        img = findViewById(R.id.imageGroupe);
        img.setImageResource(getImageSM());

        titre = findViewById(R.id.titreGroupe);
        titre.setText(getTitleSM());

        date = findViewById(R.id.dateGroupe);

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


        date.setText(startingDate.toString() + " - " + finalDate.toString());

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
                items.add(new ItemGroup("Michou", "michdu12@mail.com"));
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

}