package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Section implements Parcelable {

    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

    private String id;
    private MultiLangSectionName multiLangSectionName;
    private String feedUrl;
    private String type;
    private List<FeedPost> feedPostList;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeParcelable(multiLangSectionName, i);
        parcel.writeString(feedUrl);
        parcel.writeString(type);
        parcel.writeTypedList(feedPostList);
    }

    private Section(Parcel in) {
        this.id = in.readString();
        this.multiLangSectionName = in.readParcelable(MultiLangSectionName.class.getClassLoader());
        this.feedUrl = in.readString();
        this.type = in.readString();
        in.readTypedList(this.getFeedPostList(), FeedPost.CREATOR);
    }
}
