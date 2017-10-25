package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.dagger.module.AppModule;
import com.example.thuyhien.simplelogin.dagger.module.AuthenModule;
import com.example.thuyhien.simplelogin.dagger.module.DataCacheModule;
import com.example.thuyhien.simplelogin.dagger.module.MainModule;
import com.example.thuyhien.simplelogin.dagger.module.NetModule;
import com.example.thuyhien.simplelogin.dagger.module.PageModule;
import com.example.thuyhien.simplelogin.dagger.module.SplashModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by thuyhien on 10/24/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataCacheModule.class})
public interface AppComponent {
    void inject(FoxApplication application);

    MainComponent createMainComponent(MainModule mainModule);

    AuthenComponent createAuthenComponent(AuthenModule authenModule);

    SplashComponent createSplashComponent(SplashModule splashModule);

    PageComponent createPageComponent(PageModule pageModule);
}
