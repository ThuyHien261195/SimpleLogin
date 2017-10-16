package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MultiLangTitles implements Serializable {

    @SerializedName("names")
    private Map<String, String> multiLangTitles;

    public MultiLangTitles() {
        this.multiLangTitles = new HashMap<>();
    }

    public Map<String, String> getMultiLangTitles() {
        return multiLangTitles;
    }

    public void setMultiLangTitles(Map<String, String> multiLangTitles) {
        this.multiLangTitles = multiLangTitles;
    }

    public String getTitle(String key) {
        return this.multiLangTitles.get(key);
    }
}
