package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


        SharedPreferences userData = getSharedPreferences("test", Context.MODE_PRIVATE);
//

        SharedPreferences.Editor edit_userData = userData.edit();
        edit_userData.putInt("numberMembers", 2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Calendar cal = Calendar.getInstance();
        cal.set(2022, 10,  15);
        Date date = cal.getTime();
        String dateTime = dateFormat.format(date);
        edit_userData.putString("startingDate", dateTime);
        edit_userData.putString("lastRepetition", dateTime);


        cal.set(2021, 11,  22);
        date = cal.getTime();

        dateTime = dateFormat.format(date);
        edit_userData.putString("finalDate", dateTime);

        edit_userData.putInt("repetition", 3);
        edit_userData.commit();

        //items.add(new ItemMainActivity("test", this));
        //items.add(new ItemMainActivity("Blazy's Family", 1,R.drawable.chicken, true, 1));
        //items.add(new ItemMainActivity("Gang de Grenouille", 5,R.drawable.chicken, false, 0));
        //items.add(new ItemMainActivity("Blazy's Family", 3,R.drawable.chicken, false, 0));
//
        edit_userData = groupsName.edit();
        edit_userData.putString("test", "test");
        edit_userData.commit();


        recyclerView = findViewById(R.id.recycleViewMain);
        adapter = new MainAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button= (Button)findViewById(R.id.buttonAddMain);
        Context context = this;
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
                //items.add(new ItemMainActivity("Nouveau groupe", 3,R.drawable.chicken, false, 0));
                items.add(new ItemMainActivity("test", context));

            }
        });

    }

    private void manageRecycleView(){
        items.clear();
        for(Map.Entry<String, ?> group : groupsName.getAll().entrySet())
            items.add(new ItemMainActivity(group.getKey(),this));
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