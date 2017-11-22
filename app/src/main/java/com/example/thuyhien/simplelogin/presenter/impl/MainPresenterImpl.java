package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.manager.AppManager;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.example.thuyhien.simplelogin.view.MainView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by thuyhien on 10/9/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private WeakReference<MainView> mainViewWeakReference;
    private LoadDataInteractor loadDataInteractor;
    private UserManager userManager;
    private AppManager appManager;

    @Inject
    public MainPresenterImpl(MainView mainView,
                             LoadDataInteractor loadDataInteractor,
                             UserManager userManager,
                             AppManager appManager) {
        this.mainViewWeakReference = new WeakReference<MainView>(mainView);
        this.loadDataInteractor = loadDataInteractor;
        this.userManager = userManager;
        this.appManager = appManager;
    }

    @Override
    public void checkIsLoggedIn() {
        if (getMainView() != null) {
            if (userManager.isLoggedIn()) {
                getMainView().showLoggedInView(userManager.getEmail());
                loadPageList();
            } else {
                getMainView().showNotLogInView();
            }
        }
    }

    @Override
    public void loadPageList() {
        loadDataInteractor.getPageList(true, new LoadDataListener<List<Page>>() {
            @Override
            public void onLoadDataSuccess(final List<Page> data) {
                if (getMainView() != null) {
                    getMainView().showPageList(data);
                }
            }

            @Override
            public void onLoadDataFail(Exception ex) {
                if (getMainView() != null) {
                    getMainView().showErrorMessage(ex);
                }
            }
        });
    }

    @Override
    public void getCurrentLangCode() {
        String curLang = appManager.getUsedLanguage();
        if (getMainView() != null) {
            getMainView().getCurrentLangCode(curLang);
        }
    }

    private MainView getMainView() {
        return mainViewWeakReference.get();
    }
}
