package com.example.thuyhien.simplelogin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Page implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("names")
    private MultiLangTitles multiLangTitles;

    @SerializedName("sections")
    private List<Section> sectionList;

    public Page() {
        this.id = "";
        this.sectionList = new ArrayList<>();
    }

    public Page(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MultiLangTitles getMultiLangTitles() {
        return multiLangTitles;
    }

    public void setMultiLangTitles(MultiLangTitles multiLangTitles) {
        this.multiLangTitles = multiLangTitles;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Page page = (Page) obj;
        return this.id != null ? id.equals(page.getId()) : page.getId() == null;
    }
}
