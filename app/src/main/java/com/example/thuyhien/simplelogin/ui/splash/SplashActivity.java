package com.example.thuyhien.simplelogin.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.ui.main.MainActivity;
import com.example.thuyhien.simplelogin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity implements SplashView {

    public static final int SLEEP_MINUTES = 3000;
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenterImpl(this);
        createWaitThread();
    }

    @Override
    public void createMainIntent() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        this.finish();
    }

    @Override
    public void createWelcomeIntent() {
        Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
        startActivity(welcomeIntent);
        this.finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void createWaitThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                doThread();
                splashPresenter.checkFirstOpenApp();
            }
        }).start();
    }

    private void doThread() {
        try {
            Thread.sleep(SLEEP_MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

