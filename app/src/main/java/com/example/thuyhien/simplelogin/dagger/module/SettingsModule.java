package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.ActivityScope;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SettingsPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.SettingsActivity;
import com.example.thuyhien.simplelogin.view.SettingsView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 11/1/17.
 */

@Module
public abstract class SettingsModule {

    @ActivityScope
    @Binds
    abstract SettingsView provideSettingsView(SettingsActivity settingsActivity);

    @Binds
    abstract SettingsPresenter provideSettingsPresenter(SettingsPresenterImpl settingsPresenter);
}
