package com.example.thuyhien.simplelogin.listener;

import com.example.thuyhien.simplelogin.data.database.User;

import okhttp3.ResponseBody;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface OnAuthenticateAccountListener {
    void onAuthenticateSuccess(User user);

    void onAuthenticateFail(ResponseBody responseBody);
}
