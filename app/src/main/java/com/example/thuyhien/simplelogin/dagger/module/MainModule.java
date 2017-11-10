package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.MainPresenterImpl;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.example.thuyhien.simplelogin.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class MainModule {
    private MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    MainPresenter provideMainPresenter(LoadDataInteractor loadDataInteractor,
                                       UserManager userManager,
                                       AppManager appManager) {
        return new MainPresenterImpl(mainView, loadDataInteractor, userManager, appManager);
    }
}
