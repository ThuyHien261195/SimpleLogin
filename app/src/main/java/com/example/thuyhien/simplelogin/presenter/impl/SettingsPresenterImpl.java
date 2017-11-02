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
        updateChosenLanguage();
        if (getSettingsView() != null) {
            getSettingsView().showLanguageList(languageList);
        }
    }

    @Override
    public void saveChosenLanguage(String langCode) {
        appManager.setUsedLanguage(langCode);
    }

    private void updateChosenLanguage() {
        String langCode = appManager.getUsedLanguage();
        for (int i = 0; i < languageList.size(); i++) {
            Language language = languageList.get(i);
            if (language.getLangCode().equals(langCode)) {
                language.setSelected(true);
                return;
            }
        }
        languageList.get(0).setSelected(true);
    }

    private SettingsView getSettingsView() {
        return settingsViewWeakReference.get();
    }
}
