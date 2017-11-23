package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.ActivityScope;
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

    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindsMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract SignInActivity bindsSignInActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SignUpModule.class)
    abstract SignUpActivity bindsSignUpActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileActivity bindsProfileActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SettingsModule.class)
    abstract SettingsActivity bindsSettingsActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity bindsSplashActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = AddProfileModule.class)
    abstract AddProfileActivity bindsAddProfileActivity();
}
