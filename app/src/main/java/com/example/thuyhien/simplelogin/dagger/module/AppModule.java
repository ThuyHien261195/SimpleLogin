package com.example.thuyhien.simplelogin.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.component.MainComponent;
import com.example.thuyhien.simplelogin.dagger.component.ProfileComponent;
import com.example.thuyhien.simplelogin.dagger.component.SettingsComponent;
import com.example.thuyhien.simplelogin.dagger.component.SignInComponent;
import com.example.thuyhien.simplelogin.dagger.component.SignUpComponent;
import com.example.thuyhien.simplelogin.dagger.component.SplashComponent;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesUserManager;
import com.example.thuyhien.simplelogin.data.network.converter.FeedPostConverter;
import com.example.thuyhien.simplelogin.data.network.converter.FeedPostListConverter;
import com.example.thuyhien.simplelogin.data.network.converter.ImagePostConverter;
import com.example.thuyhien.simplelogin.data.network.converter.UserConverter;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thuyhien on 10/24/17.
 */

@Module(subcomponents = {SignInComponent.class,
        SignUpComponent.class,
        MainComponent.class,
        ProfileComponent.class,
        SettingsComponent.class,
        SplashComponent.class})
public abstract class AppModule {
    private static final String PREF_DATA_FILE_NAME = "FoxSharedPreferData";
    public static final String DI_AUTHEN_GSON = "authen_gson";
    public static final String DI_DATA_GSON = "data_gson";

    @Provides
    @Singleton
    static SharedPreferences provideSharedPref(Context context) {
        return context.getSharedPreferences(PREF_DATA_FILE_NAME, MODE_PRIVATE);
    }

    @Binds
    @Singleton
    abstract UserManager provideSharedPrefUserManager(SharedPreferencesUserManager sharedPrefUserManager);

    @Binds
    @Singleton
    abstract AppManager providerSharedPrefAppManager(SharedPreferencesAppManager sharedPrefAppManager);

    @Provides
    @Named(DI_AUTHEN_GSON)
    @Singleton
    static Gson provideAuthenGson() {
        Type userType = new TypeToken<User>() {
        }.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(userType, new UserConverter());
        return gsonBuilder.create();
    }

    @Provides
    @Named(DI_DATA_GSON)
    @Singleton
    static Gson provideDataGson() {
        Type feedListType = new TypeToken<List<MediaFeed>>() {
        }.getType();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(feedListType, new FeedPostListConverter())
                .registerTypeAdapter(MediaImage.class, new ImagePostConverter())
                .registerTypeAdapter(MediaFeed.class, new FeedPostConverter());
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    static Boolean provideIsTablet(Context context) {
        return context.getResources().getBoolean(R.bool.is_tablet);
    }

    @Provides
    @Singleton
    static List<Language> provideLanguageList(Context context) {
        String[] languageCodeList = context.getResources().getStringArray(R.array.language_code_array);
        String[] languageNameList = context.getResources().getStringArray(R.array.language_name_array);

        List<Language> languageList = new ArrayList<>();
        for (int i = 0; i < languageCodeList.length; i++) {
            languageList.add(new Language(languageCodeList[i], languageNameList[i]));
        }
        return languageList;
    }
}
