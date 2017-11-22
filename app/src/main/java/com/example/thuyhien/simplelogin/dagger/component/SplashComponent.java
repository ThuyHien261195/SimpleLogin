package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.SplashModule;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent extends AndroidInjector<SplashActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SplashActivity> {
    }
}
