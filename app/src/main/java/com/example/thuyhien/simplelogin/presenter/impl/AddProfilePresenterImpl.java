package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.data.network.model.ProfileRequest;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.utils.AuthenticationUtils;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 11/6/17.
 */

public class AddProfilePresenterImpl implements AddProfilePresenter {

    private UserManager userManager;
    private AuthenticationInteractor authenticationInteractor;
    private WeakReference<AddProfileView> addProfileViewWeakRef;

    public AddProfilePresenterImpl(UserManager userManager,
                                   AuthenticationInteractor authenticationInteractor,
                                   AddProfileView addProfileView) {
        this.userManager = userManager;
        this.authenticationInteractor = authenticationInteractor;
        this.addProfileViewWeakRef = new WeakReference<AddProfileView>(addProfileView);
    }

    @Override
    public void addProfile(String name) {
        boolean validName = false;

        try {
            validName = validateName(name);
        } catch (InvalidInputException ex) {
            if (getAddProfileView() != null) {
                getAddProfileView().showErrorMessage(ex);
            }
        }

        if (validName) {
            ProfileRequest profileRequest = new ProfileRequest(name);
            String token = userManager.getToken();
            authenticationInteractor.addProfile(token, profileRequest, new AuthenticateAccountListener<Profile>() {
                @Override
                public void onAuthenticateSuccess(Profile data) {
                    if (getAddProfileView() != null) {
                        getAddProfileView().displayAddedProfile(data);
                    }
                }

                @Override
                public void onAuthenticateFail(Exception ex) {
                    if (ex != null && ex instanceof AuthenticationException) {
                        if (getAddProfileView() != null) {
                            getAddProfileView().showErrorMessage(ex);
                        }
                    }
                }
            });
        }
    }

    private boolean validateName(String name) throws InvalidInputException {
        if (!AuthenticationUtils.checkNotEmptyInput(name)) {
            throw new InvalidInputException(InvalidInputException.ERROR_CODE_INVALID_NAME);
        }
        return true;
    }

    private AddProfileView getAddProfileView() {
        return addProfileViewWeakRef.get();
    }
}