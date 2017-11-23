package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.ActivityScope;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SplashPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.SplashActivity;
import com.example.thuyhien.simplelogin.view.SplashView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class SplashModule {

    @ActivityScope
    @Binds
    abstract SplashView provideSplashView(SplashActivity splashActivity);

    @Binds
    abstract SplashPresenter provideSplashPresenter(SplashPresenterImpl splashPresenter);
}
