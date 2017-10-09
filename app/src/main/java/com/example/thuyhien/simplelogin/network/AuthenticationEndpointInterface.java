package com.example.thuyhien.simplelogin;

import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.model.AccountRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by thuyhien on 10/5/17.
 */

public interface AuthenticationEndpointInterface {

    @POST("auth/signup")
    Call<User> signUpAccount(@Header("X-USERKIT-TOKEN") String token, @Body AccountRequest user);

    @POST("auth/signin")
    Call<User> signInAccount(@Header("X-USERKIT-TOKEN") String token, @Body AccountRequest user);
}
