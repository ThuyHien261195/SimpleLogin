package com.example.thuyhien.simplelogin.data.database;

/**
 * Created by thuyhien on 10/6/17.
 */

public class User {

    private String accountEmail;
    private String token;
    private String refreshToken;

    public User() {
        accountEmail = null;
        token = null;
        refreshToken = null;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
