package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.scope.FragmentScope;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PagePresenterImpl;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;
import com.example.thuyhien.simplelogin.view.PageView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public abstract class PageModule {

    @FragmentScope
    @Binds
    abstract PageView providePageView(PageFragment pageFragment);

    @Binds
    abstract PagePresenter providePagePresenter(PagePresenterImpl pagePresenter);
}
