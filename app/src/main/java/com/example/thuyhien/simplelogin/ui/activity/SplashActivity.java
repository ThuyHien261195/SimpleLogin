package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.view.SplashView;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((FoxApplication) getApplication()).getAppComponent()
                .createSplashComponent(new SplashModule(this))
                .inject(this);

        setContentView(R.layout.activity_splash);
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
}

