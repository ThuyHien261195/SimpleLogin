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

public class Section implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("names")
    private Map<String, String> multiLangTitles;

    @SerializedName("feed_url")
    private String feedUrl;

    @SerializedName("type")
    private String type;

    private List<MediaFeed> feedPostList;

    public Section() {
        this.id = "";
        this.multiLangTitles = new HashMap<>();
        this.feedUrl = "";
        this.type = "";
        this.feedPostList = new ArrayList<>();
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

    public List<MediaFeed> getFeedPostList() {
        return feedPostList;
    }

    public void setFeedPostList(List<MediaFeed> feedPostList) {
        this.feedPostList = feedPostList;
    }

    public String getTitle(String key) {
        return this.multiLangTitles.get(key);
    }
}
