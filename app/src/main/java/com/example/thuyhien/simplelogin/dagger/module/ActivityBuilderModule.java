package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.ui.activity.AddProfileActivity;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.example.thuyhien.simplelogin.ui.activity.ProfileActivity;
import com.example.thuyhien.simplelogin.ui.activity.SettingsActivity;
import com.example.thuyhien.simplelogin.ui.activity.SignInActivity;
import com.example.thuyhien.simplelogin.ui.activity.SignUpActivity;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by thuyhien on 11/22/17.
 */

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindsMainActivity();

    @ContributesAndroidInjector(modules = AuthenModule.class)
    abstract SignInActivity bindsSignInActivity();

    @ContributesAndroidInjector(modules = AuthenModule.class)
    abstract SignUpActivity bindsSignUpActivity();

    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileActivity bindsProfileActivity();

    @ContributesAndroidInjector(modules = SettingsModule.class)
    abstract SettingsActivity bindsSettingsActivity();

    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity bindsSplashActivity();

    @ContributesAndroidInjector(modules = AddProfileModule.class)
    abstract AddProfileActivity bindsAddProfileActivity();
}
