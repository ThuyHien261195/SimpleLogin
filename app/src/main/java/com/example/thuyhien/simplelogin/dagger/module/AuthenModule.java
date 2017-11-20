package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SignInPresenterImpl;
import com.example.thuyhien.simplelogin.presenter.impl.SignUpPresenterImpl;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class AuthenModule {
    public static final String DI_SIGN_IN_PRESENTER = "sign_in_presenter";
    public static final String DI_SIGN_UP_PRESENTER = "sign_up_presenter";

    @Binds
    @Named(DI_SIGN_IN_PRESENTER)
    abstract AuthenticatePresenter provideSignInPresenter(SignInPresenterImpl signInPresenter);

    @Binds
    @Named(DI_SIGN_UP_PRESENTER)
    abstract AuthenticatePresenter provideSignUpPresenter(SignUpPresenterImpl signUpPresenter);
}
