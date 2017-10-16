package com.example.thuyhien.simplelogin.data.manager.impl;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.model.User;

/**
 * Created by thuyhien on 10/10/17.
 */

public class SharedPreferencesUserManager implements UserManager {
    private static final String PREF_SIGNED_UP_EMAIL = "SignedUpEmail";
    private SharedPreferences sharedPref;

    public SharedPreferencesUserManager(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    public void saveUser(User user) {
        sharedPref.edit().putString(PREF_SIGNED_UP_EMAIL, user.getAccountEmail()).apply();
    }

    @Override
    public void clearUser() {
        sharedPref.edit().remove(PREF_SIGNED_UP_EMAIL).apply();
    }

    @Override
    public boolean isLoggedIn() {
        String email = sharedPref.getString(PREF_SIGNED_UP_EMAIL, "");
        return !email.equals("");
    }

    @Override
    public String getEmail() {
        return sharedPref.getString(PREF_SIGNED_UP_EMAIL, "");
    }
}
