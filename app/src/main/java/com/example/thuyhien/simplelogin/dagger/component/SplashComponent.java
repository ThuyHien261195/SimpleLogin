package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.SplashModule;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);

    @Subcomponent.Builder
    interface Builder extends BaseSubComponentBuilder<SplashComponent> {
        Builder splashModule(SplashModule splashModule);
    }
}
