package com.example.thuyhien.simplelogin.component;

import com.example.thuyhien.simplelogin.module.SplashModule;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
