package com.example.thuyhien.simplelogin.data.interactor.listener;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticateAccountListener<T> {
    void onAuthenticateSuccess(T data);

    void onAuthenticateFail(Exception ex);
}
