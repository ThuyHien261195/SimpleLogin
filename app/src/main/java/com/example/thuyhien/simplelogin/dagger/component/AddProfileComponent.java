package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.AddProfileModule;
import com.example.thuyhien.simplelogin.ui.activity.AddProfileActivity;

import dagger.Subcomponent;

/**
 * Created by thuyhien on 11/6/17.
 */

@Subcomponent(modules = {AddProfileModule.class})
public interface AddProfileComponent {
    void inject(AddProfileActivity addProfileActivity);
}
