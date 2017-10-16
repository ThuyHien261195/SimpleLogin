package com.example.thuyhien.simplelogin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Page implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("names")
    private Map<String, String> multiLangTitles;

    @SerializedName("sections")
    private List<Section> sectionList;

    public Page() {
        this.id = "";
        this.multiLangTitles = new HashMap<>();
        this.sectionList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getMultiLangTitles() {
        return multiLangTitles;
    }

    public void setMultiLangTitles(Map<String, String> multiLangTitles) {
        this.multiLangTitles = multiLangTitles;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public String getTitle(String key) {
        return this.multiLangTitles.get(key);
    }
}
