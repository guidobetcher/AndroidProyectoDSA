package com.ejemplo.pruebaparaunity;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestAPI {
    String BASE_URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";

    @GET("user")
    Call<String> userLogin(@Field("username") String username,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("user")
    Call<String> userUpDate(
            @Field("time") String time,
            @Field("gifts") String gifts,
            @Field("reindeer") String reindeer
    );
}
