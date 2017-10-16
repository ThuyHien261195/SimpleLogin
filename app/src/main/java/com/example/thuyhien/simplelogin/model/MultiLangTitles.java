package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MultiLangSectionName implements Serializable {
    private Map<String, String> multiLangTitles;

    public MultiLangSectionName() {
        this.multiLangTitles = new HashMap<>();
    }

    public Map<String, String> getMultiLangTitles() {
        return multiLangTitles;
    }

    public void setMultiLangTitles(Map<String, String> multiLangTitles) {
        this.multiLangTitles = multiLangTitles;
    }

    public void getTitle(string )
}
