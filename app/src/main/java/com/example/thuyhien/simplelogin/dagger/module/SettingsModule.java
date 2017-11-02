package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SettingsPresenterImpl;
import com.example.thuyhien.simplelogin.view.SettingsView;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/1/17.
 */

@Module
public class SettingsModule {
    private SettingsView settingsView;

    public SettingsModule(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    @Provides
    SettingsPresenter providerSettingsPresenter(AppManager appManager,
                                                List<Language> languageList) {
        return new SettingsPresenterImpl(settingsView, appManager, languageList);
    }
}
