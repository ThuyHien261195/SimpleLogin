package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PagePresenterImpl;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;
import com.example.thuyhien.simplelogin.view.PageView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class PageModule {
    PageView pageView;

    public PageModule(PageView pageView) {
        this.pageView = pageView;
    }

    @Provides
    PagePresenter providePagePresenter(LoadDataInteractor loadDataInteractor) {
        return new PagePresenterImpl(pageView, loadDataInteractor);
    }
}
