package com.example.thuyhien.simplelogin.presenter.impl;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.FileInteractor;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.view.SplashView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SplashPresenterImpl implements SplashPresenter {
    public static final int SLEEP_SECONDS = 3000;

    private WeakReference<SplashView> splashViewWeakReference;
    private AppManager appManager;
    private LoadDataInteractor loadDataInteractor;
    private FileInteractor fileInteractor;

    public SplashPresenterImpl(SplashView splashView, AppManager appManager,
                               LoadDataInteractor loadDataInteractor,
                               FileInteractor fileInteractor) {
        this.splashViewWeakReference = new WeakReference<SplashView>(splashView);
        this.appManager = appManager;
        this.fileInteractor = fileInteractor;
        this.loadDataInteractor = loadDataInteractor;
    }

    @Override
    public void loadData() {
        if (checkInternet()) {
            loadDataFromServer();
        } else {
            loadDataFromFile();
            ;
        }
    }

    private void loadDataFromServer() {
        loadDataInteractor.getPageList(new LoadDataListener<List<Page>>() {
            @Override
            public void onLoadDataSuccess(List<Page> data) {
                fileInteractor.savePageList(data);
                checkFirstOpenApp();
            }

            @Override
            public void onLoadDataFail(Exception ex) {
                ex.printStackTrace();
                checkFirstOpenApp();
            }
        });
    }

    private void loadDataFromFile() {
        // Implement thread wait temporarily
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

    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) FoxApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private SplashView getSplashView() {
        return splashViewWeakReference.get();
    }
}
