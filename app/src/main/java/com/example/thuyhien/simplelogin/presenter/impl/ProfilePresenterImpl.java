package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.DeleteProfileListener;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.view.ProfileView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 11/6/17.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    private UserManager userManager;
    private AuthenticationInteractor authenticationInteractor;
    private WeakReference<ProfileView> profileViewWeakReference;

    public ProfilePresenterImpl(UserManager userManager, AuthenticationInteractor authenticationInteractor,
                                ProfileView profileView) {
        this.userManager = userManager;
        this.authenticationInteractor = authenticationInteractor;
        this.profileViewWeakReference = new WeakReference<ProfileView>(profileView);
    }

    @Override
    public void loadProfileList() {
        showLoading();

        String token = userManager.getToken();
        authenticationInteractor.getProfileList(token, new AuthenticateAccountListener<List<Profile>>() {
            @Override
            public void onAuthenticateSuccess(List<Profile> data) {
                if (getProfileView() != null) {
                    getProfileView().hideLoading();
                    getProfileView().displayProfileList(data);
                }
            }

            @Override
            public void onAuthenticateFail(Exception ex) {
                if (getProfileView() != null) {
                    getProfileView().hideLoading();
                    getProfileView().showErrorMessage(ex);
                }
            }
        });
    }

    @Override
    public void deleteProfile(final Profile profile) {
        showLoading();

        String token = userManager.getToken();
        authenticationInteractor.deleteProfile(token, profile, new DeleteProfileListener() {
            @Override
            public void onDeleteProfileSuccess(Profile profile) {
                if (getProfileView() != null) {
                    getProfileView().hideLoading();
                    getProfileView().notifyProfileDeleteSuccess(profile);
                }
            }

            @Override
            public void onDeleteProfileFail(Exception ex) {
                if (getProfileView() != null) {
                    getProfileView().hideLoading();
                    getProfileView().showErrorMessage(ex);
                }
            }
        });
    }

    private void showLoading() {
        if (getProfileView() != null) {
            getProfileView().showLoading();
        }
    }

    private ProfileView getProfileView() {
        return profileViewWeakReference.get();
    }
}
