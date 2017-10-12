package com.example.thuyhien.simplelogin;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.network.converter.FeedPostListConverter;
import com.example.thuyhien.simplelogin.data.network.converter.PageListConverter;
import com.example.thuyhien.simplelogin.data.network.converter.UserConverter;
import com.example.thuyhien.simplelogin.data.network.retrofit.AuthenticationEndpointInterface;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.FeedPost;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thuyhien on 10/10/17.
 */

public class FoxApplication extends Application {
    public static final String AUTHENTICATION_BASE_URL = "http://userkit-identity-stg.ap-southeast-1.elasticbeanstalk.com/v1/";
    public static final String DATA_BASE_URL = "http://fox-plus-server-staging.ap-southeast-1.elasticbeanstalk.com/";
    public static final String PREF_DATA_FILE_NAME = "FoxSharedPreferData";

    private static FoxApplication instance;
    private SharedPreferences sharedPref;
    private AuthenticationEndpointInterface authenApiService;

    private DataEndpointInterface dataApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createAuthenticationRetrofit();
        createDataRetrofit();
        createSharedPreferencesFile();
    }

    public static FoxApplication getInstance() {
        return instance;
    }

    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

    public AuthenticationEndpointInterface getAuthenApiService() {
        return authenApiService;
    }

    public DataEndpointInterface getDataApiService() {
        return dataApiService;
    }

    private void createAuthenticationRetrofit() {
        Type profileType = new TypeToken<User>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(profileType, new UserConverter());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AUTHENTICATION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        authenApiService = retrofit.create(AuthenticationEndpointInterface.class);
    }

    private void createDataRetrofit() {
        Type feedListType = new TypeToken<List<FeedPost>>() {
        }.getType();
        Type pageListType = new TypeToken<List<Page>>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(pageListType, new PageListConverter())
                .registerTypeAdapter(feedListType, new FeedPostListConverter());
        Gson gson = gsonBuilder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DATA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        dataApiService = retrofit.create(DataEndpointInterface.class);
    }

    private void createSharedPreferencesFile() {
        sharedPref = getSharedPreferences(PREF_DATA_FILE_NAME, MODE_PRIVATE);
    }
}
