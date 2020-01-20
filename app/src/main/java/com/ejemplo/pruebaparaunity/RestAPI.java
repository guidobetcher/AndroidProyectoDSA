package com.ejemplo.pruebaparaunity;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {
    String BASE_URL = "https://147.83.7.205:8080/dsaApp/";

    @POST("auth/signin/")
    Call<Jugador> userLogin(@Path("username") String username,
                            @Path("password") String password);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @FormUrlEncoded
    @POST("user")
    Call<String> userUpDate(
            @Field("time") String time,
            @Field("gifts") String gifts,
            @Field("reindeer") String reindeer
    );
}
