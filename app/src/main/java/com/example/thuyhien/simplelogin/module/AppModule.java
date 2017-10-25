package com.example.thuyhien.simplelogin.module;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesUserManager;
import com.example.thuyhien.simplelogin.data.network.converter.FeedPostConverter;
import com.example.thuyhien.simplelogin.data.network.converter.FeedPostListConverter;
import com.example.thuyhien.simplelogin.data.network.converter.ImagePostConverter;
import com.example.thuyhien.simplelogin.data.network.converter.UserConverter;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thuyhien on 10/24/17.
 */

@Module
public class AppModule {
    private static final String PREF_DATA_FILE_NAME = "FoxSharedPreferData";
    private FoxApplication application;

    public AppModule(FoxApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    FoxApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPref() {
        return application.getSharedPreferences(PREF_DATA_FILE_NAME, MODE_PRIVATE);
    }

    @Provides
    @Singleton
    UserManager provideSharedPrefUserManager(SharedPreferences sharedPreferences) {
        return new SharedPreferencesUserManager(sharedPreferences);
    }

    @Provides
    @Singleton
    AppManager providerSharedPrefAppManager(SharedPreferences sharedPreferences) {
        return new SharedPreferencesAppManager(sharedPreferences);
    }

    @Provides
    @Named("authen_gson")
    @Singleton
    Gson provideAuthenGson() {
        Type profileType = new TypeToken<User>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(profileType, new UserConverter());
        return gsonBuilder.create();
    }

    @Provides
    @Named("data_gson")
    @Singleton
    Gson provideDataGson() {
        Type feedListType = new TypeToken<List<MediaFeed>>() {
        }.getType();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(feedListType, new FeedPostListConverter())
                .registerTypeAdapter(MediaImage.class, new ImagePostConverter())
                .registerTypeAdapter(MediaFeed.class, new FeedPostConverter());
        return gsonBuilder.create();
    }
}
