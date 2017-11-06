package com.example.thuyhien.simplelogin.data.interactor.impl;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.AuthenticateAccountListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.DeleteProfileListener;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.FacebookAccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.ProfileRequest;
import com.example.thuyhien.simplelogin.data.network.retrofit.AuthenticationEndpointInterface;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thuyhien on 10/9/17.
 */

public class RetrofitAuthenticationInteractor implements AuthenticationInteractor {

    private static final String JSON_PROFILES = "profiles";
    private AuthenticationEndpointInterface authenApiService;

    public RetrofitAuthenticationInteractor(AuthenticationEndpointInterface authenApiService) {
        this.authenApiService = authenApiService;
    }

    @Override
    public void signIn(AccountRequest accountRequest, final AuthenticateAccountListener<User> listener) {
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
    public void signUp(AccountRequest accountRequest, final AuthenticateAccountListener<User> listener) {
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
    public void signIntoWithFacebook(FacebookAccountRequest facebookAccountRequest,
                                     final AuthenticateAccountListener<User> listener) {
        Call<User> call = authenApiService.signIntoWithFacebookAcc(RetrofitUtils.AUTH_TOKEN,
                facebookAccountRequest);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onAuthenticateSuccess(response.body());
                } else {
                    listener.onAuthenticateFail(
                            RetrofitUtils.createFacebookAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onAuthenticateFail(new Exception(t));
            }
        });
    }

    @Override
    public void getProfileList(String token, final AuthenticateAccountListener<List<Profile>> listener) {
        Call<List<Profile>> call = authenApiService.getProfiles(token);
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onAuthenticateSuccess(response.body());
                } else {
                    listener.onAuthenticateFail(
                            RetrofitUtils.createAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                listener.onAuthenticateFail(new Exception(t));
            }
        });
    }

    @Override
    public void addProfile(String token, ProfileRequest profileRequest,
                           final AuthenticateAccountListener<Profile> listener) {
        JsonObject jsonObject = createGsonProfile(profileRequest);
        Call<List<Profile>> call = authenApiService.createProfile(token, jsonObject);
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onAuthenticateSuccess(response.body().get(0));
                } else {
                    listener.onAuthenticateFail(
                            RetrofitUtils.createAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                listener.onAuthenticateFail(new Exception(t));
            }
        });
    }

    @Override
    public void deleteProfile(String token, final Profile profile, final DeleteProfileListener listener) {
        Call<ResponseBody> call = authenApiService.deleteProfile(token, profile.getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.onDeleteProfileSuccess(profile);
                } else {
                    listener.onDeleteProfileFail(RetrofitUtils.createAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onDeleteProfileFail(new Exception(t));
            }
        });
    }

    private JsonObject createGsonProfile(ProfileRequest profileRequest) {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(gson.toJsonTree(profileRequest));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(JSON_PROFILES, jsonArray);
        return jsonObject;
    }
}
