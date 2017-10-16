package com.example.thuyhien.simplelogin.data.interactor;

import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationInteractor {
    void signIn(AccountRequest accountRequest, AuthenticateAccountListener listener);

    void signUp(AccountRequest accountRequest, AuthenticateAccountListener listener);
}
