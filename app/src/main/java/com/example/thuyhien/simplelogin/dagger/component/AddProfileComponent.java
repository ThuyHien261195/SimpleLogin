package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.AddProfileModule;
import com.example.thuyhien.simplelogin.ui.activity.AddProfileActivity;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by thuyhien on 11/6/17.
 */

@Subcomponent(modules = {AddProfileModule.class})
public interface AddProfileComponent {
    void inject(AddProfileActivity addProfileActivity);

    @Subcomponent.Builder
    interface Builder extends BaseSubComponentBuilder<AddProfileComponent> {
        @BindsInstance
        Builder bindsAddProfileActivity(AddProfileView addProfileView);
    }
}
