package com.example.thuyhien.simplelogin.data.network;

/**
 * Created by thuyhien on 10/5/17.
 */

public class AccountRequest {
    private String email;
    private String password;

    public AccountRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
