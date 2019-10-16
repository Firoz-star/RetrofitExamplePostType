package com.firozstar.retrofitexampleposttype;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;



    public static ApiInterface getClient(){

        // change your base URL
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://mobileappdatabase.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //Creating object for our interface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface;
    }
}
