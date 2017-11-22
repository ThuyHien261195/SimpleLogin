package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.AuthenModule;
import com.example.thuyhien.simplelogin.ui.activity.SignUpActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by thuyhien on 11/21/17.
 */

@Subcomponent(modules = {AuthenModule.class})
public interface SignUpComponent extends AndroidInjector<SignUpActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SignUpActivity> {
    }
}
