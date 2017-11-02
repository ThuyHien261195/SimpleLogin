package com.example.thuyhien.simplelogin.dagger.component;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 11/1/17.
 */

@Subcomponent(modules = {SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity settingsActivity);
}
