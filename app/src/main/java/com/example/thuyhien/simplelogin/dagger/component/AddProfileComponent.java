package com.example.thuyhien.simplelogin.dagger.component;

import com.example.thuyhien.simplelogin.dagger.module.AddProfileModule;
import com.example.thuyhien.simplelogin.ui.activity.AddProfileActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by thuyhien on 11/6/17.
 */

@Subcomponent(modules = {AddProfileModule.class})
public interface AddProfileComponent extends AndroidInjector<AddProfileActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AddProfileActivity> {
    }
}
