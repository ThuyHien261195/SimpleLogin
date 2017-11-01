package com.example.thuyhien.simplelogin.presenter.impl;


import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;

/**
 * Created by thuyhien on 11/1/17.
 */

public class SettingsPresenterImpl implements SettingsPresenter {

    private AppManager appManager;

    public SettingsPresenterImpl(AppManager appManager) {
        this.appManager = appManager;
    }

    @Override
    public void saveChosenLanguage() {
        appManager.setUsedLanguage();
    }
}
