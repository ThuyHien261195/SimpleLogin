package com.example.thuyhien.simplelogin;

import android.app.Application;

import com.example.thuyhien.simplelogin.dagger.component.AppComponent;
import com.example.thuyhien.simplelogin.dagger.component.DaggerAppComponent;

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
                .bindsApplication(this)
                .build();
        appComponent.inject(this);
    }
}
