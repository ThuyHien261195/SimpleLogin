package com.example.thuyhien.simplelogin;

import android.app.Application;

import com.example.thuyhien.simplelogin.component.AppComponent;
import com.example.thuyhien.simplelogin.component.DaggerAppComponent;
import com.example.thuyhien.simplelogin.module.AppModule;
import com.example.thuyhien.simplelogin.module.NetModule;

/**
 * Created by thuyhien on 10/10/17.
 */

public class FoxApplication extends Application {
    public static String langCode = "en";
    private static FoxApplication instance;
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static FoxApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        appComponent.inject(this);
        instance = this;
    }
}
