package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.MainPageAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public MainPageAdapter mainPageAdapter;
    public ArrayList<RecyclerMainPageClassType> results = new ArrayList();
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        results.add(new RecyclerMainPageClassType("Create new Resume" , "@drawable/ic_note_add_black_24dp"));
        results.add(new RecyclerMainPageClassType("Show Resume" , "@drawable/ic_search_black_24dp"));
        results.add(new RecyclerMainPageClassType("Similar Resume" , "@drawable/ic_record_voice_over_black_24dp"));
        mainPageAdapter = new MainPageAdapter(results , MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(mainPageAdapter);
    }
}
