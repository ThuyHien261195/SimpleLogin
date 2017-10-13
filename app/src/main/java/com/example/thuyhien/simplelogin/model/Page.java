package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Page implements Parcelable {

    public static final Parcelable.Creator<Page> CREATOR = new Parcelable.Creator<Page>() {
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        public Page[] newArray(int size) {
            return new Page[size];
        }
    };

    private String id;
    private MultiLangSectionName multiLangSectionName;

    private List<Section> sectionList;

    public Page() {
        this.id = "";
        this.multiLangSectionName = new MultiLangSectionName();
        this.sectionList = new ArrayList<>();
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

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeParcelable(this.multiLangSectionName, i);
        parcel.writeTypedList(this.sectionList);
    }

    private Page(Parcel in) {
        id = in.readString();
        multiLangSectionName = in.readParcelable(MultiLangSectionName.class.getClassLoader());
        in.readTypedList(getSectionList(), Section.CREATOR);
    }
}
