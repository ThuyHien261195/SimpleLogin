package com.example.thuyhien.simplelogin.ui.common;

import com.example.thuyhien.simplelogin.data.database.User;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticatePresenter {
    boolean validateAccountRequest(AccountRequest accountRequest);

    void authenticateAccountRequest(AccountRequest accountRequest);

    void updateAuthenticationEmail(User user);
}
