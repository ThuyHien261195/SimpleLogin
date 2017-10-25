package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.MainModule;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
