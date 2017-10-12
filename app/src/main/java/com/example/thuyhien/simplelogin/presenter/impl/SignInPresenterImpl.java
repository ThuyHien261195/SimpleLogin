package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnAuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.utils.AuthenticationUtils;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SignInPresenterImpl implements AuthenticatePresenter, OnAuthenticateAccountListener {

    private WeakReference<AuthenticationView> signInViewWeakPreferences;
    private AuthenticationInteractor signInInteractor;
    private UserManager userManager;

    public SignInPresenterImpl(AuthenticationView signInView,
                               AuthenticationInteractor signInInteractor,
                               UserManager userManager) {
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
    public void onAuthenticateSuccess(User user) {
        userManager.saveUser(user);
        if (getSignInView() != null) {
            getSignInView().navigateToMain();
        }
    }

    @Override
    public void onAuthenticateFail(Exception ex) {
        if (getSignInView() != null) {
            if (ex != null && ex instanceof AuthenticationException) {
                getSignInView().showMessage(ex.getMessage());
            } else {
                getSignInView().showMessage("");
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