package com.example.thuyhien.simplelogin.dagger.scope;

import android.support.v4.app.Fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;
import dagger.internal.Beta;

/**
 * Created by thuyhien on 11/22/17.
 */

@Beta
@MapKey
@Target(ElementType.METHOD)
public @interface SupportFragmentKey {
    Class<? extends Fragment> value();
}
