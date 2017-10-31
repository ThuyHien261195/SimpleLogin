package com.example.thuyhien.simplelogin.data.interactor.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.FacebookAccountRequest;
import com.example.thuyhien.simplelogin.data.network.retrofit.AuthenticationEndpointInterface;
import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thuyhien on 10/9/17.
 */

public class RetrofitAuthenticationInteractor implements AuthenticationInteractor {

    private AuthenticationEndpointInterface authenApiService;

    public RetrofitAuthenticationInteractor(AuthenticationEndpointInterface authenApiService) {
        this.authenApiService = authenApiService;
    }

    @Override
    public void signIn(AccountRequest accountRequest, final AuthenticateAccountListener listener) {
        Call<User> call = authenApiService.signInAccount(RetrofitUtils.AUTH_TOKEN, accountRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onAuthenticateSuccess(response.body());
                } else {
                    listener.onAuthenticateFail(
                            RetrofitUtils.createAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onAuthenticateFail(new Exception(t));
            }
        });
    }

    @Override
    public void signUp(AccountRequest accountRequest, final AuthenticateAccountListener listener) {
        Call<User> call = authenApiService.signUpAccount(RetrofitUtils.AUTH_TOKEN, accountRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onAuthenticateSuccess(response.body());
                } else {
                    listener.onAuthenticateFail(
                            RetrofitUtils.createAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onAuthenticateFail(new Exception(t));
            }
        });
    }

    @Override
    public void signIntoWithFacebook(FacebookAccountRequest facebookAccountRequest, final AuthenticateAccountListener listener) {
        Call<User> call = authenApiService.signIntoWithFacebookAcc(RetrofitUtils.AUTH_TOKEN, facebookAccountRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onAuthenticateSuccess(response.body());
                } else {
                    listener.onAuthenticateFail(RetrofitUtils.createFacebookAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onAuthenticateFail(new Exception(t));
            }
        });
    }
}
