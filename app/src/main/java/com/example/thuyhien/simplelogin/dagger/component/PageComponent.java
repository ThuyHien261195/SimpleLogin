package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.PageModule;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {PageModule.class})
public interface PageComponent extends AndroidInjector<PageFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PageFragment> {
    }
}
