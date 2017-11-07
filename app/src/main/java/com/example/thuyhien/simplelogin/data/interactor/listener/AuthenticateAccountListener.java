package com.example.thuyhien.simplelogin.data.interactor.listener;

import com.example.thuyhien.simplelogin.model.User;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticateAccountListener {
    void onAuthenticateSuccess(User data);

    void onAuthenticateFail(Exception ex);
}
