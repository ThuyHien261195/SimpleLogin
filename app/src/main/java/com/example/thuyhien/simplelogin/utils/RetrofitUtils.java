package com.example.thuyhien.simplelogin.utils;

import com.example.thuyhien.simplelogin.ApiEndpointInterface;
import com.example.thuyhien.simplelogin.ProfileConverter;
import com.example.thuyhien.simplelogin.model.Profile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thuyhien on 10/5/17.
 */

public class RetrofitUtils {
    public static final String BASE_URL = "http://userkit-identity-stg.ap-southeast-1.elasticbeanstalk.com/v1/";
    public static final String AUTH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9qZWN0X2lkIjoiNTg2NWU1YTdmYmE5NWU4MmE4ODA3MmJkIiwiaWF0IjoxNDY5NzkxMDc4fQ.Sv1PG7ZFooqSufUyGQHEbYRqYSVMRIANkV36HVBjVvQ";
    public static Retrofit retrofit;
    public static ApiEndpointInterface apiService;

    public static void createRetrofit() {
        Type profileType = new TypeToken<Profile>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(profileType, new ProfileConverter());
        Gson gson = gsonBuilder.create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiEndpointInterface.class);
    }
}
