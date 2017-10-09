package com.example.thuyhien.simplelogin.ui.common;

import android.content.Context;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationView {
    void setUsernameError(int error);

    void setPasswordError(int error);

    void navigateToMain();

    void showToast(String textMsg);

    Context getContext();
}
