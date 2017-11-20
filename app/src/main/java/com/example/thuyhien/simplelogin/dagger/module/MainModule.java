package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.MainPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class MainModule {
    @Binds
    abstract MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter);
}
