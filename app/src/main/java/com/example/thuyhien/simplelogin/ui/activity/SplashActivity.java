package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager;
import com.example.thuyhien.simplelogin.ui.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.ui.presenter.impl.SplashPresenterImpl;
import com.example.thuyhien.simplelogin.ui.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {

    private SplashPresenter splashPresenter;
    private FoxApplication foxApplication = FoxApplication.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        createSplashPresenter();
        splashPresenter.loadData();
    }

    @Override
    public void openMainScreen() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        this.finish();
    }

    @Override
    public void openWelcomeScreen() {
        Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
        startActivity(welcomeIntent);
        this.finish();
    }

    private void createSplashPresenter() {
        AppManager appManager = new SharedPreferencesAppManager(foxApplication.getSharedPref());
        splashPresenter = new SplashPresenterImpl(this, appManager);
    }
}

