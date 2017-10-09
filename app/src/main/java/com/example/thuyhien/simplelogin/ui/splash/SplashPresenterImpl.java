package com.example.thuyhien.simplelogin.ui.splash;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.data.prefs.SharedPreferencesHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView splashView;

    public SplashPresenterImpl(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkFirstOpenApp() {
        SharedPreferences sharedPref = splashView.getContext()
                .getSharedPreferences(SharedPreferencesHelper.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        if (SharedPreferencesHelper.getFirstOpenAppFlag(sharedPref)) {
            updateFirstOpenAppVar();
            splashView.createWelcomeIntent();
        } else {
            splashView.createMainIntent();
        }
    }

    @Override
    public void updateFirstOpenAppVar() {
        SharedPreferences sharedPref = splashView.getContext()
                .getSharedPreferences(SharedPreferencesHelper.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        SharedPreferencesHelper.saveFirstOpenAppFlag(sharedPref);
    }
}
