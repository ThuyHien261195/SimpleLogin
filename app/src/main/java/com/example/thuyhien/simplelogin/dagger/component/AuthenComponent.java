package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.AuthenModule;
import com.example.thuyhien.simplelogin.presenter.impl.SignInPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.SignInActivity;
import com.example.thuyhien.simplelogin.ui.activity.SignUpActivity;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */


@Subcomponent(modules = {AuthenModule.class})
public interface AuthenComponent {
    void inject(SignInActivity activity);

    void inject(SignUpActivity activity);

    @Subcomponent.Builder
    interface Builder extends BaseSubComponentBuilder<AuthenComponent>{
        @BindsInstance Builder bindsAuthenActivity(AuthenticationView authenticationView);
    }
}
