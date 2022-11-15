package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ItemMainActivity> items = new ArrayList<ItemMainActivity>();

        items.add(new ItemMainActivity("Blazy's Family", 1,R.drawable.chicken, true, 1));
        items.add(new ItemMainActivity("Gang de Grenouille", 5,R.drawable.chicken, false, 0));
        items.add(new ItemMainActivity("Blazy's Family", 3,R.drawable.chicken, false, 0));

        RecyclerView recyclerView = findViewById(R.id.recycleViewMain);
        MainAdapter adapter = new MainAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button= (Button)findViewById(R.id.buttonAddMain);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                items.add(new ItemMainActivity("Nouveau groupe", 3,R.drawable.chicken, false, 0));
                adapter.notifyDataSetChanged();
            }
        });
    }
}