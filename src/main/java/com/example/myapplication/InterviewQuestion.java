package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.adapter.ShowResumeAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterviewQuestion extends AppCompatActivity {

    public static ArrayList<RecieveResume> arrayList = new ArrayList<>();
    public EditText searchSkill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_question);

        searchSkill = findViewById(R.id.searchBySkill);

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });

    }

    private void callApi() {

        Call<ArrayList<RecieveResume>> searchResumeCall = RetrofitClient.getInstance().similarResume(SignUpLogin.staticEmail , searchSkill.getText().toString());

        searchResumeCall.enqueue(new Callback<ArrayList<RecieveResume>>() {
            @Override
            public void onResponse(Call<ArrayList<RecieveResume>> call, Response<ArrayList<RecieveResume>> response) {
                if(response.isSuccessful()){
                    arrayList = response.body();
                    setTheView();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecieveResume>> call, Throwable t) {

            }
        });

    }

    private void setTheView() {

        RecyclerView recyclerView = findViewById(R.id.interviewQuestionRecyclerView);
        ShowResumeAdapter showResumeAdapter = new ShowResumeAdapter(InterviewQuestion.this , arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(InterviewQuestion.this));
        recyclerView.setAdapter(showResumeAdapter);

    }
}
