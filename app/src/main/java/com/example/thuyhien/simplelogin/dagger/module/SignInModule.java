package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.ActivityScope;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SignInPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.SignInActivity;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class SignInModule {

    @ActivityScope
    @Binds
    abstract AuthenticationView provideSignInView(SignInActivity signInActivity);

    @Binds
    abstract AuthenticatePresenter provideSignInPresenter(SignInPresenterImpl signInPresenter);
}
