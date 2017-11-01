package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SettingsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/1/17.
 */

@Module
public class SettingsModule {

    @Provides
    SettingsPresenter providerSettingsPresenter(AppManager appManager) {
        return new SettingsPresenterImpl(appManager);
    }
}
