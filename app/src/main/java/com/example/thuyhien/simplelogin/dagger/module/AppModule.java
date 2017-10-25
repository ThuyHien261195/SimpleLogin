package com.example.thuyhien.simplelogin.dagger.module;

import android.content.Context;
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
    public static final String DI_AUTHEN_GSON = "authen_gson";
    public static final String DI_DATA_GSON = "data_gson";
    private Context context;

    public AppModule(FoxApplication context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return context;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPref() {
        return context.getSharedPreferences(PREF_DATA_FILE_NAME, MODE_PRIVATE);
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
    @Named(DI_AUTHEN_GSON)
    @Singleton
    Gson provideAuthenGson() {
        Type profileType = new TypeToken<User>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(profileType, new UserConverter());
        return gsonBuilder.create();
    }

    @Provides
    @Named(DI_DATA_GSON)
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
