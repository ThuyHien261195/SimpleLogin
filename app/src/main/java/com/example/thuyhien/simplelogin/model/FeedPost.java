package com.example.thuyhien.simplelogin.model;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPost {
    String id;
    String guid;
    String title;
    String description;
    ImagePost poster;

    public FeedPost() {
        this.id = "";
        this.guid = "";
        this.title = "";
        this.description = "";
        this.poster = new ImagePost();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImagePost getPoster() {
        return poster;
    }

    public void setPoster(ImagePost poster) {
        this.poster = poster;
    }
}
