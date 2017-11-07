package com.example.thuyhien.simplelogin.data.interactor;

import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.DeleteProfileListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadProfileListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.FacebookAccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.ProfileRequest;
import com.example.thuyhien.simplelogin.model.Profile;

import java.util.List;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface AuthenticationInteractor {
    void signIn(AccountRequest accountRequest, AuthenticateAccountListener listener);

    void signUp(AccountRequest accountRequest, AuthenticateAccountListener listener);

    void signIntoWithFacebook(FacebookAccountRequest facebookAccountRequest,
                              AuthenticateAccountListener listener);

    void getProfileList(String token, LoadProfileListener<List<Profile>> listener);

    void addProfile(String token, ProfileRequest profileRequest,
                    LoadProfileListener<Profile> listener);

    void deleteProfile(String token, Profile profile, DeleteProfileListener listener);
}
