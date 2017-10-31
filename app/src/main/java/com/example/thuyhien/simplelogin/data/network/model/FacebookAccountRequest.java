package com.example.thuyhien.simplelogin.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thuyhien on 10/31/17.
 */

public class FacebookAccountRequest {
    @SerializedName("provider_token")
    String token;
    String country;

    public FacebookAccountRequest(String token) {
        this.token = token;
        this.country = "VN";
    }

    public FacebookAccountRequest(String token, String country) {
        this.token = token;
        this.country = country;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
