package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.ProfileModule;
import com.example.thuyhien.simplelogin.ui.activity.ProfileActivity;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 11/6/17.
 */

@Subcomponent(modules = {ProfileModule.class})
public interface ProfileComponent {
    void inject(ProfileActivity profileActivity);
}
