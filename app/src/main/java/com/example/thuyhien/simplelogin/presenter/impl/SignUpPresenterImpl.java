package com.example.thuyhien.simplelogin.ui.presenter.impl;

import com.example.thuyhien.simplelogin.data.database.model.User;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.network.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.network.listener.OnAuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.ui.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.view.AuthenticationView;
import com.example.thuyhien.simplelogin.utils.AuthenticationUtils;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SignUpPresenterImpl implements AuthenticatePresenter, OnAuthenticateAccountListener {

    private WeakReference<AuthenticationView> signUpViewWeakPref;
    private AuthenticationInteractor signUpInteractor;
    private UserManager userManager;

    public SignUpPresenterImpl(AuthenticationView signUpView,
                               AuthenticationInteractor signUpInteractor,
                               UserManager userManager) {
        this.signUpViewWeakPref = new WeakReference<AuthenticationView>(signUpView);
        this.signUpInteractor = signUpInteractor;
        this.userManager = userManager;
    }

    @Override
    public void authenticate(String email, String password) {
        boolean validEmail = false;
        boolean validPassword = false;
        try {
            validEmail = validateEmail(email);
        } catch (InvalidInputException ex) {
            getSignUpView().showUsernameError(ex.getErrorCode());
        }

        try {
            validPassword = validatePassword(password);
        } catch (InvalidInputException ex) {
            getSignUpView().showPasswordError(ex.getErrorCode());
        }

        if (validEmail && validPassword) {
            AccountRequest accountRequest = new AccountRequest(email, password);
            signUpInteractor.signUp(accountRequest, this);
        }
    }

    @Override
    public void onAuthenticateSuccess(User user) {
        userManager.saveUser(user);
        getSignUpView().navigateToMain();
    }

    @Override
    public void onAuthenticateFail(Exception ex) {
        if (ex != null) {
            getSignUpView().showMessage(ex.getMessage());
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

    private AuthenticationView getSignUpView() {
        return signUpViewWeakPref.get();
    }
}
