package com.example.thuyhien.simplelogin.presenter;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticatePresenter {
    void authenticate(String email, String password);

    void authenticateFacebookAcc(String token);
}
