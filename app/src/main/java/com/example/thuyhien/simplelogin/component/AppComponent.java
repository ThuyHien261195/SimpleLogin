package com.example.thuyhien.simplelogin.component;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.module.AppModule;
import com.example.thuyhien.simplelogin.module.AuthenModule;
import com.example.thuyhien.simplelogin.module.DataCacheModule;
import com.example.thuyhien.simplelogin.module.MainModule;
import com.example.thuyhien.simplelogin.module.NetModule;
import com.example.thuyhien.simplelogin.module.PageModule;
import com.example.thuyhien.simplelogin.module.SplashModule;

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
