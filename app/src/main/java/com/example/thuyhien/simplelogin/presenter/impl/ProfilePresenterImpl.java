package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.DeleteProfileListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadProfileListener;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.ui.activity.ProfileActivity;
import com.example.thuyhien.simplelogin.view.ProfileView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by thuyhien on 11/6/17.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    private UserManager userManager;
    private AuthenticationInteractor authenticationInteractor;
    private WeakReference<ProfileView> profileViewWeakReference;

    @Inject
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
        authenticationInteractor.getProfileList(token, new LoadProfileListener<List<Profile>>() {
            @Override
            public void onLoadProfileSuccess(List<Profile> data) {
                if (getProfileView() != null) {
                    getProfileView().hideLoading();
                    getProfileView().displayProfileList(data);
                }
            }

            @Override
            public void onLoadProfileFail(Exception ex) {
                if (getProfileView() != null) {
                    getProfileView().hideLoading();
                    getProfileView().showErrorMessage(ex);
                }
            }
        });
    }

    @Override
    public void deleteProfileList(final List<Profile> profileList) {
        String token = userManager.getToken();
        for (final Profile profile : profileList) {
            authenticationInteractor.deleteProfile(token, profile, new DeleteProfileListener() {
                @Override
                public void onDeleteProfileSuccess(Profile profile) {
                    if (getProfileView() != null) {
                        getProfileView().updateProfileListAfterDeleting(profile);
                    }
                }

                @Override
                public void onDeleteProfileFail(Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (getProfileView() != null) {
            getProfileView().deleteProfileListSuccess();
        }
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
