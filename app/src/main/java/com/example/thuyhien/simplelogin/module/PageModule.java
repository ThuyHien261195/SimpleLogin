package com.example.thuyhien.simplelogin.module;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PagePresenterImpl;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class PageModule {
    PageFragment pageFragment;

    public PageModule(PageFragment pageFragment) {
        this.pageFragment = pageFragment;
    }

    @Provides
    PagePresenter providePagePresenter(LoadDataInteractor loadDataInteractor) {
        return new PagePresenterImpl(pageFragment, loadDataInteractor);
    }
}
