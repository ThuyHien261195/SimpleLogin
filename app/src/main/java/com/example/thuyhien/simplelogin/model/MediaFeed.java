package com.example.thuyhien.simplelogin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MediaFeed implements Serializable {
    private String id;
    private String guid;
    private String title;
    private String description;
    private List<MediaImage> thumbnails;

    public MediaFeed() {
        this.id = "";
        this.guid = "";
        this.title = "";
        this.description = "";
        this.thumbnails = null;
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

    public List<MediaImage> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<MediaImage> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
