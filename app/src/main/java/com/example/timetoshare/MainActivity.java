package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<ItemMainActivity> items;
    private RecyclerView recyclerView;
    private SharedPreferences groupsName;
    private TextView withoutGroup;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<ItemMainActivity>();
        withoutGroup = (TextView) findViewById(R.id.textWithoutGroup);
        groupsName = getSharedPreferences("groupsName", Context.MODE_PRIVATE);


        SharedPreferences.Editor edit_userData = groupsName.edit();
         //edit_userData.clear();
         //edit_userData.commit();


        recyclerView = findViewById(R.id.recycleViewMain);
        adapter = new MainAdapter(MainActivity.this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button= (Button)findViewById(R.id.buttonAddMain);
        Context context = this;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //adapter.notifyDataSetChanged();
                ////items.add(new ItemMainActivity("Nouveau groupe", 3,R.drawable.chicken, false, 0));
                //items.add(new ItemMainActivity("test", context));

                Intent myIntent = new Intent(MainActivity.this, createGroup.class);
                startActivity(myIntent);

            }
        });

    }

    private void manageRecycleView(){
        groupsName = getSharedPreferences("groupsName", Context.MODE_PRIVATE);
        items.clear();
        for(Map.Entry<String, ?> group : groupsName.getAll().entrySet()) {
            System.out.println(group.getKey());
            items.add(new ItemMainActivity(group.getKey(), this));
        }
        System.out.println("taille" + items.size());
        withoutGroup.setVisibility(items.size() > 0 ? View.INVISIBLE: View.VISIBLE);

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();


        manageRecycleView();

    }

}