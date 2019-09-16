package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.adapter.ShowResumeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowResume extends AppCompatActivity {

    public static ArrayList<RecieveResume> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_resume);

        Call<ArrayList<RecieveResume>> recieveResumeCall = RetrofitClient.getInstance().recievingResume(SignUpLogin.staticEmail);

        recieveResumeCall.enqueue(new Callback<ArrayList<RecieveResume>>() {
            @Override
            public void onResponse(Call<ArrayList<RecieveResume>> call, Response<ArrayList<RecieveResume>> response) {
                if(response.isSuccessful()){
                    arrayList = response.body();
                    Toast.makeText(ShowResume.this, arrayList.get(0).getEmail(), Toast.LENGTH_SHORT).show();
                    setTheView();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecieveResume>> call, Throwable t) {

            }
        });

    }

    private void setTheView() {

        RecyclerView recyclerView = findViewById(R.id.showResumeRecyclerView);
        ShowResumeAdapter showResumeAdapter = new ShowResumeAdapter(ShowResume.this , arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowResume.this));
        recyclerView.setAdapter(showResumeAdapter);

    }
}
