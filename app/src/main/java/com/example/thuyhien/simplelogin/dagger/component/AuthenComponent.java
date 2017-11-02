package com.example.thuyhien.simplelogin.dagger.component;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */


@Subcomponent(modules = {AuthenModule.class})
public interface AuthenComponent {
    void inject(SignInActivity activity);

    void inject(SignUpActivity activity);
}
