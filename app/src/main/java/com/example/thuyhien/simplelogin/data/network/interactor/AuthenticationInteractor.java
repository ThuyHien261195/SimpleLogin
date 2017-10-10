package com.example.thuyhien.simplelogin.data.network.interactor;

import com.example.thuyhien.simplelogin.data.network.listener.OnAuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationInteractor {
    void signIn(AccountRequest accountRequest, OnAuthenticateAccountListener listener);

    void signUp(AccountRequest accountRequest, OnAuthenticateAccountListener listener);
}
