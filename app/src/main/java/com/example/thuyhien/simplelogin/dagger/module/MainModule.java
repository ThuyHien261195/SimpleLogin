package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.MainPresenterImpl;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class MainModule {
    @Binds
    abstract MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter);

    @ContributesAndroidInjector(modules = PageModule.class)
    abstract PageFragment bindsPageFragment();
}
