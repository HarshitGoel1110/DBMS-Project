package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpLogin extends AppCompatActivity {

    EditText signupEmail,signUppass;
    Button loginButton;
    TextView signUpLink;
    public static String staticEmail = null , staticPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        signupEmail = findViewById(R.id.signUpEmail);
        signUppass = findViewById(R.id.signUpPassword);
        loginButton = findViewById(R.id.loginButton);
        signUpLink = findViewById(R.id.signUpLink);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signupEmail.getText().toString();
                String pass = signUppass.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUpLogin.this , "Kindly filled the required entries" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(loginButton.getText().equals("Login"))
                    login(email , pass);
                else
                    signUp(email , pass);
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the layout of the file
                loginButton.setText("Sign Up");
                signUpLink.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void signUp(final String email,final String pass) {


        Call<Message> sign = RetrofitClient.getInstance().signUser(email , pass);
        sign.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                try {
                    String result = response.body().getMessage();
                    if(result.equals("success...")) {
                        Toast.makeText(SignUpLogin.this, "Sign in Successfully...\nPlease log in to continue...", Toast.LENGTH_SHORT).show();
                        SignUpLogin.staticEmail = email;
                        SignUpLogin.staticPass = pass;
                        Intent intent = new Intent(SignUpLogin.this, SignUpLogin.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SignUpLogin.this, "please check your email/password...", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(SignUpLogin.this, "the email id should be unique...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });

    }

    private void login(final String email, final String pass) {

        // code for login
        // calling the api with retrofit
        // here we are calling the function loginUser which we have created in the interface

        Call<Message> login = RetrofitClient
                .getInstance()
                .loginUser(email , pass);

        // for calling the http method we need to enqueue the method

        login.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                try {
                    String result = response.body().getMessage();
                    if(result.equals("success...")) {
                        Toast.makeText(SignUpLogin.this, "Successfully logged in...", Toast.LENGTH_SHORT).show();
                        staticEmail = email;
                        staticPass = pass;
                        Intent intent = new Intent(SignUpLogin.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        //Toast.makeText(SignUpLogin.this, "please check your email/password...", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(SignUpLogin.this, "some internal error has occurred...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(SignUpLogin.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
