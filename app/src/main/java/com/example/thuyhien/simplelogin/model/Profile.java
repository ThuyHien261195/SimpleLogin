package com.example.thuyhien.simplelogin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thuyhien on 11/3/17.
 */

public class Profile implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private String id;

    public Profile(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
