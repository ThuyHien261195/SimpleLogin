package com.example.thuyhien.simplelogin;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.database.model.User;
import com.example.thuyhien.simplelogin.data.database.model.converter.UserConverter;
import com.example.thuyhien.simplelogin.data.network.retrofit.AuthenticationEndpointInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thuyhien on 10/10/17.
 */

public class FoxApplication extends Application {
    public static final String BASE_URL = "http://userkit-identity-stg.ap-southeast-1.elasticbeanstalk.com/v1/";
    public static final String PREF_DATA_FILE_NAME = "FoxSharedPreferData";

    private static FoxApplication instance;
    private SharedPreferences sharedPref;
    private AuthenticationEndpointInterface apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createRetrofit();
        createSharedPreferencesFile();
    }

    public static FoxApplication getInstance() {
        return instance;
    }

    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

    public void setSharedPref(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    public AuthenticationEndpointInterface getApiService() {
        return apiService;
    }

    public void setApiService(AuthenticationEndpointInterface apiService) {
        this.apiService = apiService;
    }

    private void createRetrofit() {
        Type profileType = new TypeToken<User>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(profileType, new UserConverter());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(AuthenticationEndpointInterface.class);
    }

    private void createSharedPreferencesFile() {
        sharedPref = getSharedPreferences(PREF_DATA_FILE_NAME, MODE_PRIVATE);
    }
}
