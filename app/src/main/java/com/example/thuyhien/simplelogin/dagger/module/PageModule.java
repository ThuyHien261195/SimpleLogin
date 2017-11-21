package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PagePresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class PageModule {
    @Binds
    abstract PagePresenter providePagePresenter(PagePresenterImpl pagePresenter);
}
