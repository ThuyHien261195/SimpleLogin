package com.example.thuyhien.simplelogin;

import android.app.Activity;
import android.app.Application;

import com.example.thuyhien.simplelogin.dagger.component.AppComponent;
import com.example.thuyhien.simplelogin.dagger.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by thuyhien on 10/10/17.
 */

public class FoxApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .bindsApplication(this)
                .build();
    }
}
