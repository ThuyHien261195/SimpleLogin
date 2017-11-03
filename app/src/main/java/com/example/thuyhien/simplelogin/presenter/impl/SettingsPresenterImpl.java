package com.example.thuyhien.simplelogin.presenter.impl;


import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.view.SettingsView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 11/1/17.
 */

public class SettingsPresenterImpl implements SettingsPresenter {

    private WeakReference<SettingsView> settingsViewWeakReference;
    private AppManager appManager;
    private List<Language> languageList;

    public SettingsPresenterImpl(SettingsView settingsView, AppManager appManager,
                                 List<Language> languageList) {
        this.appManager = appManager;
        this.languageList = languageList;
        this.settingsViewWeakReference = new WeakReference<SettingsView>(settingsView);
    }

    @Override
    public void getLanguageList() {
        String usedLanguageCode = appManager.getUsedLanguage();
        if (getSettingsView() != null) {
            getSettingsView().showLanguageList(languageList, usedLanguageCode);
        }
    }

    @Override
    public void saveChosenLanguage(Language chosenLang) {
        appManager.setUsedLanguage(chosenLang.getLangCode());
        if (getSettingsView() != null) {
            getSettingsView().reloadAppAfterChangeLanguage();
        }
    }

    private SettingsView getSettingsView() {
        return settingsViewWeakReference.get();
    }
}
