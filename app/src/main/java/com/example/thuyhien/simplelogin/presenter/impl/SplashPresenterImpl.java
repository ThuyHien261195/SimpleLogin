package com.example.thuyhien.simplelogin.ui.presenter.impl;

import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.ui.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.ui.view.SplashView;

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
            getSplashView().openMainScreen();
        } else {
            appManager.setAlreadyUsedApp();
            getSplashView().openWelcomeScreen();
        }
    }

    private SplashView getSplashView() {
        return splashViewWeakReference.get();
    }
}
