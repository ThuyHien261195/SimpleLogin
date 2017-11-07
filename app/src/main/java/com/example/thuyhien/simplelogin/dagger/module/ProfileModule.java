package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.ProfilePresenterImpl;
import com.example.thuyhien.simplelogin.view.ProfileView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public class ProfileModule {

    private static final int NUM_COLUMN_TABLET = 3;
    private static final int NUM_COLUMN_PHONE = 2;
    private final ProfileView profileView;

    public ProfileModule(ProfileView profileView) {
        this.profileView = profileView;
    }

    @Provides
    ProfilePresenter provileProfilePresenter(UserManager userManager,
                                             AuthenticationInteractor authenticationInteractor) {
        return new ProfilePresenterImpl(userManager, authenticationInteractor, profileView);
    }

    @Provides
    int provideProfileGridColumn(boolean isTablet) {
        if (isTablet) {
            return NUM_COLUMN_TABLET;
        } else {
            return NUM_COLUMN_PHONE;
        }
    }
}
