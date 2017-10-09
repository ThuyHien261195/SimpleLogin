package com.example.thuyhien.simplelogin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.utils.SharedPreferencesUtils;

public class SplashActivity extends AppCompatActivity {

    public static final int SLEEP_MINUTES = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        createWaitThread();
    }

    private void createWaitThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                doThread();
                startMainApp();
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

    private void startMainApp() {
        SharedPreferences sharedPref = this.getSharedPreferences(SharedPreferencesUtils.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        if (SharedPreferencesUtils.getFirstOpenAppFlag(sharedPref)) {
            SharedPreferencesUtils.saveFirstOpenAppFlag(sharedPref);
            Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeIntent);
        } else {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }
        this.finish();
    }
}
