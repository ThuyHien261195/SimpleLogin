package com.example.thuyhien.simplelogin.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thuyhien on 10/11/17.
 */

public class ImagePost implements Parcelable{

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(imageTitle);
    }

    private ImagePost(Parcel in) {
        this.imageUrl = in.readString();
        this.imageTitle = in.readString();
    }
}
