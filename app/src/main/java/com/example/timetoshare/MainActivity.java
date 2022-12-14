package com.example.timetoshare;

import static com.example.timetoshare.Active.AFTER;
import static com.example.timetoshare.Active.BEFORE;
import static com.example.timetoshare.Active.PROGRESS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
    TextView nameView;

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


        List<ItemMainActivity> tmps = new ArrayList<ItemMainActivity>();
        for(Map.Entry<String, ?> group : groupsName.getAll().entrySet()) {
            System.out.println(group.getKey());
            tmps.add(new ItemMainActivity(group.getKey(), this));
        }

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        //    NotificationChannel channel = new NotificationChannel("My notif", "My notif", NotificationManager.IMPORTANCE_DEFAULT);
        //    NotificationManager manager = getSystemService(NotificationManager.class);
        //    manager.createNotificationChannel(channel);
        //}

       // nameView = itemView.findViewById(R.id.name_itemMain);
        //Intent myIntent = new Intent(context, sendingMessage.class);
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //myIntent.putExtra("groupName", nameView.getText().toString());
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, myIntent, PendingIntent.FLAG_IMMUTABLE);

        //for(ItemMainActivity item : tmps){
        //    if(item.needMessage){
        //        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "My notif");
        //        builder.setContentTitle("TimeToShare");
        //        builder.setContentText("Pensez ?? envoyer un message ?? votre groupe !");
        //        builder.setSmallIcon(R.drawable.logo);
        //        //builder.setContentIntent(pendingIntent);
        //        builder.setAutoCancel(true);

        //        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        //        managerCompat.notify(1,builder.build());
        //    }
        //}


    }

    private void manageRecycleView(){
        groupsName = getSharedPreferences("groupsName", Context.MODE_PRIVATE);
        items.clear();
        List<ItemMainActivity> tmps = new ArrayList<ItemMainActivity>();
        for(Map.Entry<String, ?> group : groupsName.getAll().entrySet()) {
            System.out.println(group.getKey());
            tmps.add(new ItemMainActivity(group.getKey(), this));
        }

        //Mettre les cloche en premier
        for(ItemMainActivity item : tmps){
            if(item.needMessage){
                items.add(item);
            }
        }

        //Mettre les actif sans cloche en deuxieme
        for(ItemMainActivity item : tmps){
            if(item.active == PROGRESS && !item.needMessage){
                items.add(item);
            }
        }

        //Mettre les groupe pas encore pass?? en troisi??me
        for(ItemMainActivity item : tmps){
            if(item.active == BEFORE){
                items.add(item);
            }
        }

        //Mettre les groupes d??j?? pass?? en dernier
        for(ItemMainActivity item : tmps) {
            if (item.active == AFTER) {
                items.add(item);
            }
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