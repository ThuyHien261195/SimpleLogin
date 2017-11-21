package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.ProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.ProfilePresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public abstract class ProfileModule {

    private static final int NUM_COLUMN_TABLET = 3;
    private static final int NUM_COLUMN_PHONE = 2;

    @Binds
    abstract ProfilePresenter provileProfilePresenter(ProfilePresenterImpl profilePresenter);

    @Provides
    static int provideProfileGridColumn(boolean isTablet) {
        if (isTablet) {
            return NUM_COLUMN_TABLET;
        } else {
            return NUM_COLUMN_PHONE;
        }
    }
}
