package com.example.thuyhien.simplelogin.module;

import android.app.Activity;

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
    private Activity activity;

    public AuthenModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Named("sign_in_presenter")
    AuthenticatePresenter provideSignInPresenter(AuthenticationInteractor authenInteractor,
                                                 UserManager userManager) {
        return new SignInPresenterImpl((AuthenticationView) activity, userManager, authenInteractor);
    }

    @Provides
    @Named("sign_up_presenter")
    AuthenticatePresenter provideSignUpPresenter(AuthenticationInteractor authenInteractor,
                                                 UserManager userManager) {
        return new SignUpPresenterImpl((AuthenticationView) activity, userManager, authenInteractor);
    }
}
