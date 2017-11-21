package com.example.thuyhien.simplelogin.presenter.impl;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.view.SplashView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private WeakReference<SplashView> splashViewWeakReference;
    private AppManager appManager;
    private LoadDataInteractor loadDataInteractor;
    private Context context;

    @Inject
    public SplashPresenterImpl(SplashView splashView, AppManager appManager,
                               LoadDataInteractor loadDataInteractor, Context context) {
        this.splashViewWeakReference = new WeakReference<SplashView>(splashView);
        this.appManager = appManager;
        this.loadDataInteractor = loadDataInteractor;
        this.context = context;
    }

    @Override
    public void loadData() {
        if (checkInternet()) {
            loadDataInteractor.getPageList(false, new LoadDataListener<List<Page>>() {
                @Override
                public void onLoadDataSuccess(List<Page> data) {
                    checkFirstOpenApp();
                }

                @Override
                public void onLoadDataFail(Exception ex) {
                    ex.printStackTrace();
                    checkFirstOpenApp();
                }
            });
        } else {
            checkFirstOpenApp();
        }
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
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private SplashView getSplashView() {
        return splashViewWeakReference.get();
    }
}
