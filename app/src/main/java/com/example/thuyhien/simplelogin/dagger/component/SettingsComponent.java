package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.SettingsModule;
import com.example.thuyhien.simplelogin.ui.activity.SettingsActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by thuyhien on 11/1/17.
 */

@Subcomponent(modules = {SettingsModule.class})
public interface SettingsComponent extends AndroidInjector<SettingsActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SettingsActivity> {
    }
}
