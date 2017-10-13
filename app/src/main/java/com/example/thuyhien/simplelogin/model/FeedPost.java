package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPost implements Parcelable {

    public static final Parcelable.Creator<FeedPost> CREATOR =
            new Parcelable.Creator<FeedPost>() {
                public FeedPost createFromParcel(Parcel in) {
                    return new FeedPost(in);
                }

                public FeedPost[] newArray(int size) {
                    return new FeedPost[size];
                }
            };

    private String id;
    private String guid;
    private String title;
    private String description;
    private ImagePost poster;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(guid);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeParcelable(poster, i);
    }

    private FeedPost(Parcel in) {
        this.id = in.readString();
        this.guid = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.poster = in.readParcelable(ImagePost.class.getClassLoader());
    }
}
