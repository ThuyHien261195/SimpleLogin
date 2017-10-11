package com.example.thuyhien.simplelogin.model;

/**
 * Created by thuyhien on 10/11/17.
 */

public class ImagePost {
    String imageUrl;
    String imageTitle;

    public ImagePost() {
        this.imageUrl = "";
        this.imageTitle = "";
    }

    public ImagePost(String imageUrl, String imageTitle) {
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
