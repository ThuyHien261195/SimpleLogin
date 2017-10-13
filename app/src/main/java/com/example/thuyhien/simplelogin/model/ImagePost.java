package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.thuyhien.simplelogin.utils.ImageUtils;

/**
 * Created by thuyhien on 10/11/17.
 */

public class ImagePost implements Parcelable {

    public static final Parcelable.Creator<ImagePost> CREATOR =
            new Parcelable.Creator<ImagePost>() {
                public ImagePost createFromParcel(Parcel in) {
                    return new ImagePost(in);
                }

                public ImagePost[] newArray(int size) {
                    return new ImagePost[size];
                }
            };

    private String imageUrl;
    private String imageTitle;
    private int width;
    private int height;

    public ImagePost() {
        this.imageUrl = "";
        this.imageTitle = "";
        this.width = ImageUtils.REQUEST_WIDTH;
        this.height = ImageUtils.REQUEST_HEIGHT;
    }

    public ImagePost(String imageUrl, String imageTitle) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(imageTitle);
        parcel.writeInt(width);
        parcel.writeInt(height);
    }

    private ImagePost(Parcel in) {
        this.imageUrl = in.readString();
        this.imageTitle = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }
}
