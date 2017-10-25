package com.example.thuyhien.simplelogin.module;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SplashPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class SplashModule {
    private SplashActivity splashActivity;

    public SplashModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Provides
    SplashPresenter provideSplashPresenter(AppManager appManager, LoadDataInteractor loadDataInteractor) {
        return new SplashPresenterImpl(splashActivity, appManager, loadDataInteractor);
    }
}
