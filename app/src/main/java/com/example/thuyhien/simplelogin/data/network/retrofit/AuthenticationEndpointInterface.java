package com.example.thuyhien.simplelogin.data.network.retrofit;

import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.network.model.FacebookAccountRequest;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.model.User;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by thuyhien on 10/5/17.
 */

public interface AuthenticationEndpointInterface {

    String X_USERKIT_TOKEN = "X-USERKIT-TOKEN";

    @POST("v1/auth/signup")
    Call<User> signUpAccount(@Header(X_USERKIT_TOKEN) String token, @Body AccountRequest user);

    @POST("v1/auth/signin")
    Call<User> signInAccount(@Header(X_USERKIT_TOKEN) String token, @Body AccountRequest user);

    @POST("v1/auth/facebook")
    Call<User> signIntoWithFacebookAcc(@Header(X_USERKIT_TOKEN) String token,
                                       @Body FacebookAccountRequest user);

    @GET("v1/profiles")
    Call<List<Profile>> getProfiles(@Header(X_USERKIT_TOKEN) String accountToken);

    @POST("v1/profiles")
    Call<List<Profile>> createProfile(@Header(X_USERKIT_TOKEN) String accountToken,
                                      @Body JsonObject profileRequestJson);

    @DELETE("v1/profiles/{id}")
    Call<ResponseBody> deleteProfile(@Header(X_USERKIT_TOKEN) String accountToken,
                                     @Path("id") String profileId);
}
