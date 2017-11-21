package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SplashPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class SplashModule {
    @Binds
    abstract SplashPresenter provideSplashPresenter(SplashPresenterImpl splashPresenter);
}
