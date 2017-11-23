package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.ActivityScope;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SignUpPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.SignUpActivity;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 11/23/17.
 */

@Module
public abstract class SignUpModule {

    @ActivityScope
    @Binds
    abstract AuthenticationView provideSignUpView(SignUpActivity signUpActivity);

    @Binds
    abstract AuthenticatePresenter provideSignUpPresenter(SignUpPresenterImpl signUpPresenter);
}
