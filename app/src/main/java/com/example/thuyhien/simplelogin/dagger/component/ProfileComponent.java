package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.ProfileModule;
import com.example.thuyhien.simplelogin.ui.activity.ProfileActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by thuyhien on 11/6/17.
 */

@Subcomponent(modules = {ProfileModule.class})
public interface ProfileComponent extends AndroidInjector<ProfileActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProfileActivity> {
    }
}
