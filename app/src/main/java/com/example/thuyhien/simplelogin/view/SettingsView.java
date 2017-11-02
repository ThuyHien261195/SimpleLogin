package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.Language;

import java.util.List;

import butterknife.BindView;

/**
 * Created by thuyhien on 11/2/17.
 */

public interface SettingsView {
    void showLanguageList(List<Language> languageList);
}
