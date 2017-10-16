package com.example.thuyhien.simplelogin.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPost implements Serializable {
    private String id;
    private String guid;
    private String title;
    private String description;
    private List<ImagePost> thumbnails;

    public FeedPost() {
        this.id = "";
        this.guid = "";
        this.title = "";
        this.description = "";
        this.thumbnails = new ArrayList<>();
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

    public List<ImagePost> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<ImagePost> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
