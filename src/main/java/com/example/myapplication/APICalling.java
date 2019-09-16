package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APICalling {

    // this is the place where we will define our API calls

    // for Login
    // in Call we will specify the return type of the call which we are making
    // then we will define the method implementing it
    // in this method we will pass on the field value to make this call
    // we will make use of FormUrlEncoded as we are giving the arguments
    @POST("users/login/{email}/{password}")
    Call<Message> loginUser (@Path("email") String email , @Path("password") String password);

    @POST("users/signup/{email}/{password}")
    Call<Message> signUser (@Path("email") String email , @Path("password") String password);

    // for creating the resume
    @POST("users/upload/{email}")
    Call<Message> uploadResume(@Path("email")String email ,@Body Resume resume);

    // for showing the resume
    @GET("users/get/{email}")
    Call<ArrayList<RecieveResume>> recievingResume(@Path("email") String email);

    @GET("users/similarskills/{email}/{myskill}")
    Call<ArrayList<RecieveResume>> similarResume(@Path("email")String email , @Path("myskill")String myskill);


}
