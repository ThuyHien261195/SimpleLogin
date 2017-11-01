package com.example.thuyhien.simplelogin.view;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationView extends BaseView {
    void showUsernameError(String errorCode);

    void showPasswordError(String errorCode);

    void navigateToMain();
}
