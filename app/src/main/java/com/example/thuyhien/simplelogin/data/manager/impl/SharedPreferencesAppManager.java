package com.example.thuyhien.simplelogin.data.manager.impl;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.manager.AppManager;

/**
 * Created by thuyhien on 10/10/17.
 */

public class SharedPreferencesAppManager implements AppManager {
    private static final String PREF_ALREADY_USED = "AlreadyUsed";
    public static final String LANGUAGE_CODE = "LanguageCode";
    public static final String DEFAULT_LANGUAGE_CODE = "en";

    private SharedPreferences sharedPref;

    public SharedPreferencesAppManager(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }


    @Override
    public boolean isAlreadyUsedApp() {
        return sharedPref.getBoolean(PREF_ALREADY_USED, false);
    }

    @Override
    public void setAlreadyUsedApp() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PREF_ALREADY_USED, true);
        editor.apply();
    }

    @Override
    public String getUsedLanguage() {
        return sharedPref.getString(LANGUAGE_CODE, DEFAULT_LANGUAGE_CODE);
    }

    @Override
    public void setUsedLanguage(String langCode) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(LANGUAGE_CODE, langCode);
        editor.apply();
    }
}
