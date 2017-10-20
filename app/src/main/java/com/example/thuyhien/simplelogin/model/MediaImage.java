package com.example.thuyhien.simplelogin.model;

import com.example.thuyhien.simplelogin.utils.ImageUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MediaImage implements Serializable {

    @SerializedName("url")
    private String imageUrl;

    @SerializedName("title")
    private String imageTitle;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    public MediaImage() {
        this.imageUrl = "";
        this.imageTitle = "";
        this.width = ImageUtils.REQUEST_WIDTH;
        this.height = ImageUtils.REQUEST_HEIGHT;
    }

    public MediaImage(String imageUrl, String imageTitle) {
        this.imageUrl = imageUrl;
        this.imageTitle = imageTitle;
        this.width = ImageUtils.REQUEST_WIDTH;
        this.height = ImageUtils.REQUEST_HEIGHT;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
