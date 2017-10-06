package com.example.thuyhien.simplelogin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/6/17.
 */

public class Profile {

    private String accountEmail;
    private String token;

    public Profile() {
        accountEmail = null;
        token = null;
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
}
