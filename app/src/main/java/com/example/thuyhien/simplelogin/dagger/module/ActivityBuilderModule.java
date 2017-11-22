package com.example.thuyhien.simplelogin.dagger.module;

import android.app.Activity;

import com.example.thuyhien.simplelogin.dagger.component.MainComponent;
import com.example.thuyhien.simplelogin.dagger.component.ProfileComponent;
import com.example.thuyhien.simplelogin.dagger.component.SettingsComponent;
import com.example.thuyhien.simplelogin.dagger.component.SignInComponent;
import com.example.thuyhien.simplelogin.dagger.component.SignUpComponent;
import com.example.thuyhien.simplelogin.dagger.component.SplashComponent;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.example.thuyhien.simplelogin.ui.activity.ProfileActivity;
import com.example.thuyhien.simplelogin.ui.activity.SettingsActivity;
import com.example.thuyhien.simplelogin.ui.activity.SignInActivity;
import com.example.thuyhien.simplelogin.ui.activity.SignUpActivity;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by thuyhien on 11/22/17.
 */

@Module
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsMainActivity(MainComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SignInActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsSignInActivity(SignInComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SignUpActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsSignUpActivity(SignUpComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(ProfileActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsProfileActivity(ProfileComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SettingsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsSettingsActivity(SettingsComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsSplashActivity(SplashComponent.Builder builder);
}
