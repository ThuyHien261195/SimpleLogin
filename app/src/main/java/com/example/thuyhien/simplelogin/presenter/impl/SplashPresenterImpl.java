package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.view.SplashView;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SplashPresenterImpl implements SplashPresenter {
    public static final int SLEEP_SECONDS = 3000;

    private WeakReference<SplashView> splashViewWeakReference;
    private AppManager appManager;

    public SplashPresenterImpl(SplashView splashView, AppManager appManager) {
        this.splashViewWeakReference = new WeakReference<SplashView>(splashView);
        this.appManager = appManager;
    }

    @Override
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SLEEP_SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkFirstOpenApp();
            }
        }).start();
    }

    private void checkFirstOpenApp() {
        if (appManager.isAlreadyUsedApp()) {
            if (getSplashView() != null) {
                getSplashView().openMainScreen();
            }
        } else {
            if (getSplashView() != null) {
                appManager.setAlreadyUsedApp();
                getSplashView().openWelcomeScreen();
            }
        }
    }

    private SplashView getSplashView() {
        return splashViewWeakReference.get();
    }
}
