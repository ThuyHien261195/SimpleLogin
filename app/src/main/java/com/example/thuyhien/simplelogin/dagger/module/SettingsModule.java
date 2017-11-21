package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SettingsPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 11/1/17.
 */

@Module
public abstract class SettingsModule {
    @Binds
    abstract SettingsPresenter providerSettingsPresenter(SettingsPresenterImpl settingsPresenter);
}
