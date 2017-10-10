package com.example.thuyhien.simplelogin.data.manager.impl;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.manager.AppManager;

/**
 * Created by thuyhien on 10/10/17.
 */

public class SharedPreferencesAppManager implements AppManager {
    private static final String PREF_ALREADY_USED = "AlreadyUsed";

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
}
