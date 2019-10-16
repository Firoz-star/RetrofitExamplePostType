package com.firozstar.retrofitexampleposttype;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/retrofit/register.php")
    Call<SignUpResponse> registration(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("logintype") String logintype
    );
}
