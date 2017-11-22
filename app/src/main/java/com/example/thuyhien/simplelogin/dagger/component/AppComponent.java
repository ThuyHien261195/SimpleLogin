package com.example.thuyhien.simplelogin.dagger.component;

import android.content.Context;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.dagger.module.ActivityBuilderModule;
import com.example.thuyhien.simplelogin.dagger.module.AppModule;
import com.example.thuyhien.simplelogin.dagger.module.DataCacheModule;
import com.example.thuyhien.simplelogin.dagger.module.NetModule;
import com.example.thuyhien.simplelogin.ui.fragment.MediaFeedDialogFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by thuyhien on 10/24/17.
 */

@Singleton
@Component(modules = {AppModule.class,
        NetModule.class,
        DataCacheModule.class,
        ActivityBuilderModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>{

    boolean isTablet();

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder bindsApplication(Context context);
    }
}
