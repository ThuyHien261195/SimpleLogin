package com.example.thuyhien.simplelogin.dagger.component;

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

@Module
public abstract class ActivityBindingModule {
    @Binds
    @Singleton
    abstract UserManager provideSharedPrefUserManager(SharedPreferencesUserManager sharedPrefUserManager);

    @Binds
    @Singleton
    abstract AppManager providerSharedPrefAppManager(SharedPreferencesAppManager sharedPrefAppManager);
}
