package com.example.thuyhien.simplelogin.data.network.model;

/**
 * Created by thuyhien on 11/3/17.
 */

public class ProfileRequest {
    private String name;

    public ProfileRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
