package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.ActivityScope;
import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.AddProfilePresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.AddProfileActivity;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public abstract class AddProfileModule {

    @ActivityScope
    @Binds
    abstract AddProfileView provideProfileView(AddProfileActivity addProfileActivity);

    @Binds
    abstract AddProfilePresenter provideAddProfilePresenter(AddProfilePresenterImpl addProfilePresenter);
}
