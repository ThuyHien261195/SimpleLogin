package com.example.thuyhien.simplelogin.dagger.module;

import android.content.Context;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SplashPresenterImpl;
import com.example.thuyhien.simplelogin.view.SplashView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class SplashModule {
    private SplashView splashView;

    public SplashModule(SplashView splashView) {
        this.splashView = splashView;
    }

    @Provides
    SplashPresenter provideSplashPresenter(AppManager appManager, LoadDataInteractor loadDataInteractor, Context context) {
        return new SplashPresenterImpl(splashView, appManager, loadDataInteractor, context);
    }
}
