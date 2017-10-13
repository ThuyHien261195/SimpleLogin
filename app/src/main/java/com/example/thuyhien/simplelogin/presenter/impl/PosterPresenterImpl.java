package com.example.thuyhien.simplelogin.presenter.impl;

import android.graphics.Bitmap;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadImageListener;
import com.example.thuyhien.simplelogin.presenter.PosterPresenter;
import com.example.thuyhien.simplelogin.view.PosterView;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/12/17.
 */

public class PosterPresenterImpl implements PosterPresenter, OnLoadImageListener {

    private WeakReference<PosterView> posterViewWeakReference;
    private LoadDataInteractor loadDataInteractor;

    public PosterPresenterImpl(PosterView posterView, LoadDataInteractor loadDataInteractor) {
        this.posterViewWeakReference = new WeakReference<PosterView>(posterView);
        this.loadDataInteractor = loadDataInteractor;
    }

    @Override
    public void loadPoster(String imageUrl) {
        loadDataInteractor.getPoster(imageUrl, this);
    }

    @Override
    public void onLoadDataSuccess(Bitmap bitmap) {
        if (getPageView() != null) {
            getPageView().showPoster(bitmap);
        }
    }

    @Override
    public void onLoadDataFail(Exception ex) {
        if (getPageView() != null) {
            getPageView().showErrorLoadPoster(ex);
        }
    }

    private PosterView getPageView() {
        return posterViewWeakReference.get();
    }
}