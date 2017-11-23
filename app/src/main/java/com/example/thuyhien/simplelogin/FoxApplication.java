package com.example.thuyhien.simplelogin;

import com.example.thuyhien.simplelogin.dagger.component.AppComponent;
import com.example.thuyhien.simplelogin.dagger.component.DaggerAppComponent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by thuyhien on 10/10/17.
 */

public class FoxApplication extends DaggerApplication {

    private static GoogleAnalytics googleAnalytics;
    private static Tracker tracker;
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        googleAnalytics = GoogleAnalytics.getInstance(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder()
                .bindsApplication(this)
                .build();
        return appComponent;
    }

    synchronized public Tracker getDefaultTracker() {
        if (tracker == null) {
            tracker = googleAnalytics.newTracker(R.xml.global_tracker);
            tracker.enableAutoActivityTracking(true);
        }
        return tracker;
    }
}
