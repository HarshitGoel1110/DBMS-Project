package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayTheResume extends AppCompatActivity {

    TextView ename,eemail,ephNumber,eaddress,edob,emaritalStatus,ecity,estate,ecountry,epincode ,egender;
    TextView efirstSkill , efirstAchievement , efirstHobby , eDeclaration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_the_resume);

        Intent intent = getIntent();
        int position = intent.getIntExtra("resume" , -1);
        if(position == -1)
            return;

        RecieveResume recieveResume = ShowResume.arrayList.get(position);

        ename = findViewById(R.id.displayName);
        eemail = findViewById(R.id.displayEmail);
        ephNumber = findViewById(R.id.displayPhnumber);
        eaddress = findViewById(R.id.displayAddress);
        edob = findViewById(R.id.displayDob);
        emaritalStatus = findViewById(R.id.displayMarital);
        ecity = findViewById(R.id.displayCity);
        estate = findViewById(R.id.displayState);
        ecountry = findViewById(R.id.displayCountry);
        epincode = findViewById(R.id.displayPincode);
        efirstSkill = findViewById(R.id.displaySkills);
        efirstAchievement = findViewById(R.id.displayAchievement);
        efirstHobby = findViewById(R.id.displayHobby);
        eDeclaration = findViewById(R.id.displayDeclaration);
        egender = findViewById(R.id.displayGender);

        ename.setText(ename.getText().toString() + recieveResume.getName());
        eemail.setText(eemail.getText().toString() + recieveResume.getEmail());
        ephNumber.setText(ephNumber.getText().toString() + recieveResume.getPhnumber());
        eaddress.setText(eaddress.getText().toString() + recieveResume.getAddress());
        edob.setText(edob.getText().toString() + recieveResume.getDateofbirth());
        emaritalStatus.setText(emaritalStatus.getText().toString() + recieveResume.getMaritalStatus());
        ecity.setText(ecity.getText().toString() + recieveResume.getCity());
        estate.setText(estate.getText().toString() + recieveResume.getState());
        ecountry.setText(ecountry.getText().toString() + recieveResume.getCountry());
        epincode.setText(epincode.getText().toString() + recieveResume.getPincode());
        egender.setText(egender.getText().toString() + recieveResume.getGender());
        efirstSkill.setText(efirstSkill.getText().toString() + recieveResume.getSkill()[0] +" " + recieveResume.getSkill()[1]);
        efirstAchievement.setText(efirstAchievement.getText().toString() + recieveResume.getAchievement()[0] +" " + recieveResume.getAchievement()[1]);
        efirstHobby.setText(efirstHobby.getText().toString() + recieveResume.getHobby()[0] +" " + recieveResume.getHobby()[1]);
        eDeclaration.setText(eDeclaration.getText().toString() + recieveResume.getDeclaration());

    }
}
