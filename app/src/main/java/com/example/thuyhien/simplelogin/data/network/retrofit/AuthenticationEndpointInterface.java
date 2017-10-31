package com.example.thuyhien.simplelogin.data.network.retrofit;

import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.FacebookAccountRequest;
import com.example.thuyhien.simplelogin.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by thuyhien on 10/5/17.
 */

public interface AuthenticationEndpointInterface {

    @POST("v1/auth/signup")
    Call<User> signUpAccount(@Header("X-USERKIT-TOKEN") String token, @Body AccountRequest user);

    @POST("v1/auth/signin")
    Call<User> signInAccount(@Header("X-USERKIT-TOKEN") String token, @Body AccountRequest user);

    @POST("v1/auth/facebook")
    Call<User> signIntoWithFacebookAcc(@Header("X-USERKIT-TOKEN") String token,
                                       @Body FacebookAccountRequest user);
}
