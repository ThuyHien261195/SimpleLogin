package com.example.thuyhien.simplelogin.dagger.component;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
