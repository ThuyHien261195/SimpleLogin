package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.AddProfilePresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public abstract class AddProfileModule {

    @Binds
    abstract AddProfilePresenter provideAddProfilePresenter(AddProfilePresenterImpl addProfilePresenter);
}
