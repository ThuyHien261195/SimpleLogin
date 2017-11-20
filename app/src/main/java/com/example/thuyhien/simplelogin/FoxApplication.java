package com.example.thuyhien.simplelogin;

import android.app.Application;

import com.example.thuyhien.simplelogin.dagger.component.AppComponent;
import com.example.thuyhien.simplelogin.dagger.component.DaggerAppComponent;
import com.example.thuyhien.simplelogin.dagger.module.AppModule;

/**
 * Created by thuyhien on 10/10/17.
 */

public class FoxApplication extends Application {
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
