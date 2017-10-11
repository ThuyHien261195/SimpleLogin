package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.view.MainView;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/9/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private WeakReference<MainView> mainViewWeakReference;
    private UserManager userManager;

    public MainPresenterImpl(MainView mainView, UserManager userManager) {
        this.mainViewWeakReference = new WeakReference<>(mainView);
        this.userManager = userManager;
    }

    @Override
    public void checkIsLoggedIn() {
        if (userManager.isLoggedIn()) {
            if (getMainView() != null) {
                getMainView().showLoggedInView();
            }
        }
    }

    private MainView getMainView() {
        return mainViewWeakReference.get();
    }
}
