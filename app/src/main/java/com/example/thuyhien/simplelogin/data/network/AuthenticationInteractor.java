package com.example.thuyhien.simplelogin.data.network;

import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.listener.OnAuthenticateAccountListener;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationInteractor {
    void signIn(AccountRequest accountRequest, OnAuthenticateAccountListener listener);

    void signUp(AccountRequest accountRequest, OnAuthenticateAccountListener listener);
}
