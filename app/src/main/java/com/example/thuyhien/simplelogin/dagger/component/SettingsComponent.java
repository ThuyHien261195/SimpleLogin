package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.SettingsModule;
import com.example.thuyhien.simplelogin.ui.activity.SettingsActivity;
import com.example.thuyhien.simplelogin.view.SettingsView;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by thuyhien on 11/1/17.
 */

@Subcomponent(modules = {SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity settingsActivity);

    @Subcomponent.Builder
    interface Builder extends BaseSubComponentBuilder<SettingsComponent> {
        @BindsInstance
        Builder bindsSettingsActivity(SettingsView settingsView);
    }
}
