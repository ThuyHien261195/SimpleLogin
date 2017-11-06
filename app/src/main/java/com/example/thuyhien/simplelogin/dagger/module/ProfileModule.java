package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.ProfilePresenterImpl;
import com.example.thuyhien.simplelogin.view.ProfileView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public class ProfileModule {

    private final ProfileView profileView;

    public ProfileModule(ProfileView profileView) {
        this.profileView = profileView;
    }

    @Provides
    ProfilePresenter profileProfilePresenter(UserManager userManager,
                                             AuthenticationInteractor authenticationInteractor) {
        return new ProfilePresenterImpl(userManager, authenticationInteractor, profileView);
    }
}
