package com.example.thuyhien.simplelogin.component;

import com.example.thuyhien.simplelogin.module.AuthenModule;
import com.example.thuyhien.simplelogin.ui.activity.SignInActivity;
import com.example.thuyhien.simplelogin.ui.activity.SignUpActivity;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */


@Subcomponent(modules = {AuthenModule.class})
public interface AuthenComponent {
    void inject(SignInActivity activity);

    void inject(SignUpActivity activity);
}
