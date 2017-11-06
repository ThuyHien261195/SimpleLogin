package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.AddProfilePresenterImpl;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 11/6/17.
 */

@Module
public class AddProfileModule {

    private final AddProfileView addProfileView;

    public AddProfileModule(AddProfileView addProfileView) {
        this.addProfileView = addProfileView;
    }

    @Provides
    AddProfilePresenter provideAddProfilePresenter(UserManager userManager,
                                                   AuthenticationInteractor authenticationInteractor) {
        return new AddProfilePresenterImpl(userManager, authenticationInteractor, addProfileView);
    }
}
