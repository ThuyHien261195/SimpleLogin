package com.example.thuyhien.simplelogin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Section {
    String id;
    MultiLangSectionName multiLangSectionName;
    String feedUrl;
    String type;
    List<FeedPost> feedPostList;

    public Section() {
        this.id = "";
        this.multiLangSectionName = new MultiLangSectionName();
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

    public MultiLangSectionName getMultiLangSectionName() {
        return multiLangSectionName;
    }

    public void setMultiLangSectionName(MultiLangSectionName multiLangSectionName) {
        this.multiLangSectionName = multiLangSectionName;
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

    public List<FeedPost> getFeedPostList() {
        return feedPostList;
    }

    public void setFeedPostList(List<FeedPost> feedPostList) {
        this.feedPostList = feedPostList;
    }
}
