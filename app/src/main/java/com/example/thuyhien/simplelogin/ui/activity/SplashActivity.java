package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.interactor.DataCache;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.FileDataCache;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitLoadDataInteractor;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.presenter.SplashPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.SplashPresenterImpl;
import com.example.thuyhien.simplelogin.view.SplashView;

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
        DataEndpointInterface dataApiService = foxApplication.getDataApiService();
        DataCache dataCache = new FileDataCache(foxApplication.getDataGson(),
                foxApplication.getPageDir(),
                foxApplication.getFeedDir());
        LoadDataInteractor loadDataInteractor = new RetrofitLoadDataInteractor(dataApiService, dataCache);
        splashPresenter = new SplashPresenterImpl(this, appManager, loadDataInteractor);
    }
}

