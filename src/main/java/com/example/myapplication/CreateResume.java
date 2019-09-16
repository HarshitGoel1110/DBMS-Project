package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateResume extends AppCompatActivity implements PersonalInformation.OnFragmentInteractionListener , CarrerObjective.OnFragmentInteractionListener {

    public static final int IMAGE_CODE = 1;
    public String imageString = "";
    public String gender = "";
    String name, email, phnumber, address, dob, maritalAddress, city, state, country, pincode ;
    String firstSkill , secondSkill = "" , firstAchievement, secondAchievement = "" , firstHobby , secondHobby = "" , declaration;

    public static final int READ_STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resume);

        TabLayout tabLayout = findViewById(R.id.tabLayoutPagination);
        tabLayout.addTab(tabLayout.newTab().setText("Personal Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Carrier Objectives"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPagerResume);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager() , tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // working on the toolbar

        Toolbar toolbar = findViewById(R.id.createResumeToolbar);
        toolbar.inflateMenu(R.menu.create_resume_toolar_file);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.createResumeBackButton)
                    startActivity(new Intent(CreateResume.this , MainActivity.class));
                return false;
            }
        });


    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.maleResume :
                if(checked)
                    gender = "male";
                break;

            case R.id.femaleResume :
                if(checked)
                    gender = "female";
                break;
        }

    }

    public void saveAndMoveBack(View view) {

        TextInputLayout ename,eemail,ephNumber,eaddress,edob,emaritalStatus,ecity,estate,ecountry,epincode;
        TextInputLayout efirstSkill , esecondSkill , efirstAchievement , esecondAchievement, efirstHobby , esecondHobby , eDeclaration;
        ename = findViewById(R.id.resumeName);
        eemail = findViewById(R.id.resumeEmail);
        ephNumber = findViewById(R.id.resumePhoneNumber);
        eaddress = findViewById(R.id.resumeAddress);
        edob = findViewById(R.id.resumeDOB);
        emaritalStatus = findViewById(R.id.resumeMaritalStatus);
        ecity = findViewById(R.id.resumeCity);
        estate = findViewById(R.id.resumeState);
        ecountry = findViewById(R.id.resumeCountry);
        epincode = findViewById(R.id.resumePincode);
        efirstSkill = findViewById(R.id.firstSkill);
        esecondSkill = findViewById(R.id.secondSkill);
        efirstAchievement = findViewById(R.id.firstAchievement);
        esecondAchievement = findViewById(R.id.secondAchievement);
        efirstHobby = findViewById(R.id.firstHobby);
        esecondHobby = findViewById(R.id.secondHobby);
        eDeclaration = findViewById(R.id.firstDeclare);

        RadioGroup radioGroup = findViewById(R.id.resumeRadioGroup);

        try {
            name = ename.getEditText().getText().toString();
            email = eemail.getEditText().getText().toString();
            phnumber = ephNumber.getEditText().getText().toString();
            address = eaddress.getEditText().getText().toString();
            dob = edob.getEditText().getText().toString();
            maritalAddress = emaritalStatus.getEditText().getText().toString();
            city = ecity.getEditText().getText().toString();
            state = estate.getEditText().getText().toString();
            country = ecountry.getEditText().getText().toString();
            pincode = epincode.getEditText().getText().toString();
            if(gender == "")
                throw new Exception("abc");
            firstSkill = efirstSkill.getEditText().getText().toString();
            firstAchievement = efirstAchievement.getEditText().getText().toString();
            firstHobby = efirstHobby.getEditText().getText().toString();
            declaration = eDeclaration.getEditText().getText().toString();
        }

        catch(Exception e){
            Toast.makeText(this, "Please fill all the entries to proceed further...", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            secondSkill = esecondSkill.getEditText().getText().toString();
        }
        catch(Exception e){

        }

        try{
            secondAchievement = esecondAchievement.getEditText().getText().toString();
        }
        catch(Exception e){

        }

        try{
            secondHobby = esecondHobby.getEditText().getText().toString();
        }
        catch(Exception e){

        }

        uploadTheResume();

    }

    private void uploadTheResume() {

        Resume resume = new Resume(
                name,email,phnumber,address,dob,maritalAddress,city,state,country,pincode,gender,
                new String[]{firstSkill,secondSkill} , new String[]{firstAchievement,secondAchievement},
                new String[]{firstHobby,secondHobby} , declaration
        );

        Log.i("====" , resume.toString());

        Call<Message> uploadResume = RetrofitClient.getInstance().uploadResume(resume.email , resume);

        uploadResume.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()){
                    if(response.body().getMessage().equals("success...")) {
                        Toast.makeText(CreateResume.this, "Uploaded successfully...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateResume.this , MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(CreateResume.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CreateResume.this, "please Check your internet connection...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });

    }

    public void chooseAnImage(View view){
        if(ActivityCompat.checkSelfPermission(CreateResume.this , Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            selectANImage();
        }
        else{
            ActivityCompat.requestPermissions(CreateResume.this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , READ_STORAGE_PERMISSION_CODE);
        }
    }

    public void selectANImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_CODE && resultCode == RESULT_OK && data!=null){
            imageString = data.getData().toString();
            Picasso.get().load(imageString).fit().into((ImageButton)findViewById(R.id.profileImageResume));
        }
    }


}
