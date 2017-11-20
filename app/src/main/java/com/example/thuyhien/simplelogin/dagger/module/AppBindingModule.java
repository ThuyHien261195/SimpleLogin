package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.component.AuthenComponent;
import com.example.thuyhien.simplelogin.dagger.component.MainComponent;
import com.example.thuyhien.simplelogin.dagger.component.PageComponent;
import com.example.thuyhien.simplelogin.dagger.component.ProfileComponent;
import com.example.thuyhien.simplelogin.dagger.component.SettingsComponent;
import com.example.thuyhien.simplelogin.dagger.component.SplashComponent;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesUserManager;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 11/20/17.
 */

@Module(subcomponents = {AuthenComponent.class, MainComponent.class, PageComponent.class,
        ProfileComponent.class, SettingsComponent.class, SplashComponent.class})
public abstract class AppBindingModule {

    @Binds
    @Singleton
    abstract UserManager provideSharedPrefUserManager(SharedPreferencesUserManager sharedPrefUserManager);

    @Binds
    @Singleton
    abstract AppManager providerSharedPrefAppManager(SharedPreferencesAppManager sharedPrefAppManager);
}
