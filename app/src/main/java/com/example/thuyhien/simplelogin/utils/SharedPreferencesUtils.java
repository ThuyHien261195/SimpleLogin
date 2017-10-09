package com.example.thuyhien.simplelogin.utils;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.model.User;

/**
 * Created by thuyhien on 10/5/17.
 */

public class SharedPreferencesUtils {
    public static final String PREF_DATA_FILE_NAME = "FoxSharedPreferData";
    public static final String PREF_FIRST_OPEN_APP = "FirstOpenApp";
    public static final String PREF_SIGNED_UP_EMAIL = "SignedUpEmail";

    public static void saveFirstOpenAppFlag(SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PREF_FIRST_OPEN_APP, false);
        editor.apply();
    }

    public static void saveSignedUpEmail(SharedPreferences sharedPref, User user) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PREF_SIGNED_UP_EMAIL, user.getAccountEmail());
        editor.apply();
    }

    public static boolean getFirstOpenAppFlag(SharedPreferences sharedPref) {
        return sharedPref.getBoolean(PREF_FIRST_OPEN_APP, true);
    }

    public static String getSignedUpEmail(SharedPreferences sharedPref) {
        return sharedPref.getString(PREF_SIGNED_UP_EMAIL, "");
    }

    public static boolean isLoggedIn(SharedPreferences sharedPref) {
        String email = getSignedUpEmail(sharedPref);
        return !email.equals("");
    }
}
