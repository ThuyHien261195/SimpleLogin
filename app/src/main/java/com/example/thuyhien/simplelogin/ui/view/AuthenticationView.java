package com.example.thuyhien.simplelogin.ui.view;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationView {
    void showUsernameError(String errorCode);

    void showPasswordError(String errorCode);

    void navigateToMain();

    void showMessage(String textMsg);
}
