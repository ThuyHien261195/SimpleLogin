package com.example.thuyhien.simplelogin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Section implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("names")
    private MultiLangTitles multiLangTitles;

    @SerializedName("feed_url")
    private String feedUrl;

    @SerializedName("type")
    private String type;

    public Section() {
        this.id = "";
        this.feedUrl = "";
        this.type = "";
    }

    public Section(String id) {
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

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        return id != null ? id.equals(section.id) : section.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
