package com.example.thuyhien.simplelogin;

import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by thuyhien on 10/5/17.
 */

public interface ApiEndpointInterface {

    @POST("auth/signup")
    Call<Profile> signUpAccount(@Header("X-USERKIT-TOKEN") String token, @Body User user);

    @POST("auth/signin")
    Call<Profile> signInAccount(@Header("X-USERKIT-TOKEN") String token, @Body User user);
}
