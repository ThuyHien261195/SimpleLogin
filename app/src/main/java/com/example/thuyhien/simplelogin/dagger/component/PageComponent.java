package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.PageModule;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;
import com.example.thuyhien.simplelogin.view.PageView;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by thuyhien on 10/25/17.
 */

@Subcomponent(modules = {PageModule.class})
public interface PageComponent {
    void inject(PageFragment pageFragment);

    @Subcomponent.Builder
    interface Builder extends BaseSubComponentBuilder<PageComponent> {
        @BindsInstance
        Builder bindsPageActivity(PageView pageView);
    }
}
