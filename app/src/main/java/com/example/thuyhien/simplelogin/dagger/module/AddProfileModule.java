package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.AddProfilePresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.example.thuyhien.simplelogin.view.MainView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public abstract class AddProfileModule {

    @Binds
    abstract MainView provideMainView(MainActivity mainActivity);

    @Binds
    abstract AddProfilePresenter provideAddProfilePresenter(AddProfilePresenterImpl addProfilePresenter);
}
