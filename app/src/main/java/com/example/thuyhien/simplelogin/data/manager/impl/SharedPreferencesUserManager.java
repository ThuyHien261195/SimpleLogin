package com.example.thuyhien.simplelogin.data.manager.impl;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.model.User;

/**
 * Created by thuyhien on 10/10/17.
 */

public class SharedPreferencesUserManager implements UserManager {
    private static final String PREF_SIGNED_UP_EMAIL = "SignedUpEmail";
    private static final String PREF_TOKEN = "Token";
    private static final String PREF_REFRESHED_TOKEN = "RefreshedToken";

    private SharedPreferences sharedPref;

    public SharedPreferencesUserManager(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    @Override
    public void saveUser(User user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_SIGNED_UP_EMAIL, user.getAccountEmail());
        editor.putString(PREF_TOKEN, user.getToken());
        editor.putString(PREF_REFRESHED_TOKEN, user.getRefreshToken());
        editor.apply();
    }

    @Override
    public void clearUser() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(PREF_SIGNED_UP_EMAIL);
        editor.remove(PREF_TOKEN);
        editor.remove(PREF_REFRESHED_TOKEN);
        editor.apply();
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

    @Override
    public String getToken() {
        return sharedPref.getString(PREF_TOKEN, "");
    }
}
