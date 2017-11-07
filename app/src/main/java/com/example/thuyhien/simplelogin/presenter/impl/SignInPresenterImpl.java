package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.data.network.exception.FacebookAuthenticationException;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.FacebookAccountRequest;
import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.utils.AuthenticationUtils;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SignInPresenterImpl implements AuthenticatePresenter, AuthenticateAccountListener {

    private WeakReference<AuthenticationView> signInViewWeakPreferences;
    private AuthenticationInteractor signInInteractor;
    private UserManager userManager;

    public SignInPresenterImpl(AuthenticationView signInView,
                               UserManager userManager,
                               AuthenticationInteractor signInInteractor) {
        this.signInViewWeakPreferences = new WeakReference<AuthenticationView>(signInView);
        this.signInInteractor = signInInteractor;
        this.userManager = userManager;
    }

    @Override
    public void authenticate(String email, String password) {
        boolean validEmail = false;
        boolean validPassword = false;
        try {
            validEmail = validateEmail(email);
        } catch (InvalidInputException ex) {
            if (getSignInView() != null) {
                getSignInView().showUsernameError(ex.getErrorCode());
            }
        }

        try {
            validPassword = validatePassword(password);
        } catch (InvalidInputException ex) {
            if (getSignInView() != null) {
                getSignInView().showPasswordError(ex.getErrorCode());
            }
        }

        AccountRequest accountRequest = new AccountRequest(email, password);
        if (validEmail && validPassword) {
            signInInteractor.signIn(accountRequest, this);
        }
    }

    @Override
    public void authenticateFacebookAcc(String token) {
        FacebookAccountRequest facebookAccountRequest = new FacebookAccountRequest(token);
        signInInteractor.signIntoWithFacebook(facebookAccountRequest, this);
    }

    @Override
    public void onAuthenticateSuccess(User data) {
        userManager.saveUser(data);
        if (getSignInView() != null) {
            getSignInView().navigateToMain();
        }
    }

    @Override
    public void onAuthenticateFail(Exception ex) {
        if (ex != null && ((ex instanceof AuthenticationException)
                || (ex instanceof FacebookAuthenticationException))) {
            if (getSignInView() != null) {
                getSignInView().showErrorMessage(ex);
            }
        }
    }


    private boolean validateEmail(String email) throws InvalidInputException {
        if (!AuthenticationUtils.checkNotEmptyInput(email)) {
            throw new InvalidInputException(InvalidInputException.ERROR_CODE_EMPTY_EMAIL);
        } else if (!AuthenticationUtils.checkValidEmail(email)) {
            throw new InvalidInputException(InvalidInputException.ERROR_CODE_INVALID_EMAIL);
        }
        return true;
    }

    private boolean validatePassword(String password) throws InvalidInputException {
        if (!AuthenticationUtils.checkNotEmptyInput(password)) {
            throw new InvalidInputException(InvalidInputException.ERROR_CODE_EMPTY_PASSWORD);
        } else if (!AuthenticationUtils.checkValidPass(password)) {
            throw new InvalidInputException(InvalidInputException.ERROR_CODE_INVALID_PASSWORD);
        }
        return true;
    }

    private AuthenticationView getSignInView() {
        return signInViewWeakPreferences.get();
    }
}
