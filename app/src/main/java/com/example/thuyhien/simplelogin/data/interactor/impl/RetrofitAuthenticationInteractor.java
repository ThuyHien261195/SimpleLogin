package com.example.thuyhien.simplelogin.data.interactor.impl;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnAuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thuyhien on 10/9/17.
 */

public class RetrofitAuthenticationInteractor implements AuthenticationInteractor {

    @Override
    public void signIn(AccountRequest accountRequest, final OnAuthenticateAccountListener listener) {
        Call<User> call = FoxApplication.getInstance().getAuthenApiService()
                .signInAccount(RetrofitUtils.AUTH_TOKEN, accountRequest);
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
    public void signUp(AccountRequest accountRequest, final OnAuthenticateAccountListener listener) {
        Call<User> call = FoxApplication.getInstance().getAuthenApiService()
                .signUpAccount(RetrofitUtils.AUTH_TOKEN, accountRequest);
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
}
