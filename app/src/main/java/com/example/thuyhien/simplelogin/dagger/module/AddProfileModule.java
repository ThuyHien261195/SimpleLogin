package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.AddProfilePresenterImpl;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public abstract class AddProfileModule {

    @Binds
    abstract AddProfilePresenter provideAddProfilePresenter(AddProfilePresenterImpl addProfilePresenter);
}
