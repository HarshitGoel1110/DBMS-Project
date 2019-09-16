package com.example.myapplication;

import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // we will use this class for defining the retrofit object

    public static final String BASE_URL = "https://infinite-reaches-59604.herokuapp.com/";
    public static Retrofit retrofit = null;

    public static APICalling getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APICalling.class);
    }

}
