package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.FileInteractor;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.view.MainView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 10/9/17.
 */

public class MainPresenterImpl implements MainPresenter {

    private WeakReference<MainView> mainViewWeakReference;
    private UserManager userManager;
    private LoadDataInteractor loadDataInteractor;
    private FileInteractor fileInteractor;

    public MainPresenterImpl(MainView mainView, UserManager userManager,
                             LoadDataInteractor loadDataInteractor,
                             FileInteractor fileInteractor) {
        this.mainViewWeakReference = new WeakReference<>(mainView);
        this.userManager = userManager;
        this.loadDataInteractor = loadDataInteractor;
        this.fileInteractor = fileInteractor;
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
        fileInteractor.getPageList(new LoadDataListener<List<Page>>() {
            @Override
            public void onLoadDataSuccess(List<Page> data) {
                if (data == null) {
                    getPageListFromServer();
                } else {
                    if (getMainView() != null) {
                        getMainView().showPageList(data);
                    }
                }
            }

            @Override
            public void onLoadDataFail(Exception e) {
                e.printStackTrace();

                getPageListFromServer();
            }
        });
    }

    private void getPageListFromServer() {
        loadDataInteractor.getPageList(new LoadDataListener<List<Page>>() {
            @Override
            public void onLoadDataSuccess(final List<Page> data) {
                fileInteractor.savePageList(data);

                if (getMainView() != null) {
                    getMainView().showPageList(data);
                }
            }

            @Override
            public void onLoadDataFail(Exception ex) {
                if (getMainView() != null) {
                    getMainView().showMessageError(ex);
                }
            }
        });
    }

    private MainView getMainView() {
        return mainViewWeakReference.get();
    }
}
