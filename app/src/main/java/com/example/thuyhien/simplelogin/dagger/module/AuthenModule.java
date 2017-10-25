package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SignInPresenterImpl;
import com.example.thuyhien.simplelogin.presenter.impl.SignUpPresenterImpl;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class AuthenModule {
    public static final String DI_SIGN_IN_PRESENTER = "sign_in_presenter";
    public static final String DI_SIGN_UP_PRESENTER = "sign_up_presenter";
    private AuthenticationView authenticationView;

    public AuthenModule(AuthenticationView authenticationView) {
        this.authenticationView = authenticationView;
    }

    @Provides
    @Named(DI_SIGN_IN_PRESENTER)
    AuthenticatePresenter provideSignInPresenter(AuthenticationInteractor authenInteractor,
                                                 UserManager userManager) {
        return new SignInPresenterImpl(authenticationView, userManager, authenInteractor);
    }

    @Provides
    @Named(DI_SIGN_UP_PRESENTER)
    AuthenticatePresenter provideSignUpPresenter(AuthenticationInteractor authenInteractor,
                                                 UserManager userManager) {
        return new SignUpPresenterImpl(authenticationView, userManager, authenInteractor);
    }
}
