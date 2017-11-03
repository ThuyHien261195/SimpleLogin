package com.example.thuyhien.simplelogin.presenter;

import com.example.thuyhien.simplelogin.model.Language;

/**
 * Created by thuyhien on 11/1/17.
 */

public interface SettingsPresenter {
    void getLanguageList();

    void saveChosenLanguage(Language chosenLang);
}
