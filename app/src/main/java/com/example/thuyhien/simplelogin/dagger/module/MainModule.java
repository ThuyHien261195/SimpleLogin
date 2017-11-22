package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.dagger.component.PageComponent;
import com.example.thuyhien.simplelogin.dagger.scope.SupportFragmentKey;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.MainPresenterImpl;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module(subcomponents = PageComponent.class)
public abstract class MainModule {
    @Binds
    abstract MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter);

    @Binds
    @IntoMap
    @SupportFragmentKey(PageFragment.class)
    abstract AndroidInjector.Factory<? extends android.support.v4.app.Fragment> bindsPageFragment(PageComponent.Builder builder);
}
