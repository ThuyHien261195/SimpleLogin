package com.example.thuyhien.simplelogin.ui.main;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.prefs.SharedPreferencesHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thuyhien on 10/9/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void checkIsLoggedIn() {
        SharedPreferences sharedPref = mainView.getContext()
                .getSharedPreferences(SharedPreferencesHelper.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        boolean isLoggedIn = SharedPreferencesHelper.isLoggedIn(sharedPref);
        if (isLoggedIn) {
            mainView.setLoggedInView();
        }
    }
}
