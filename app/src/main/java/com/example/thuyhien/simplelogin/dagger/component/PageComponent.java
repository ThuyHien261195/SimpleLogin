package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.PageModule;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {PageModule.class})
public interface PageComponent {
    void inject(PageFragment pageFragment);

    @Subcomponent.Builder
    interface Builder extends BaseSubComponentBuilder<PageComponent>{
        Builder pageModule(PageModule pageModule);
    }
}
