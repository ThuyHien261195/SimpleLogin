package com.example.thuyhien.simplelogin.data.network.listener;

import com.example.thuyhien.simplelogin.data.database.model.User;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface OnAuthenticateAccountListener {
    void onAuthenticateSuccess(User user);

    void onAuthenticateFail(Exception ex);
}
