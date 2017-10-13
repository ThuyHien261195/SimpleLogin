package com.example.thuyhien.simplelogin.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MultiLangSectionName implements Parcelable {

    public static final Parcelable.Creator<MultiLangSectionName> CREATOR =
            new Parcelable.Creator<MultiLangSectionName>() {
                public MultiLangSectionName createFromParcel(Parcel in) {
                    return new MultiLangSectionName(in);
                }

                public MultiLangSectionName[] newArray(int size) {
                    return new MultiLangSectionName[size];
                }
            };

    private String zhHantName;
    private String englishName;
    private String zhHansName;
    private String atVNName;

    public MultiLangSectionName() {
        this.zhHantName = "";
        this.englishName = "";
        this.zhHansName = "";
        this.atVNName = "";
    }

    public String getZhHantName() {
        return zhHantName;
    }

    public void setZhHantName(String zhHantName) {
        this.zhHantName = zhHantName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getZhHansName() {
        return zhHansName;
    }

    public void setZhHansName(String zhHansName) {
        this.zhHansName = zhHansName;
    }

    public String getAtVNName() {
        return atVNName;
    }

    public void setAtVNName(String atVNName) {
        this.atVNName = atVNName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(zhHantName);
        parcel.writeString(englishName);
        parcel.writeString(zhHansName);
        parcel.writeString(atVNName);
    }

    private MultiLangSectionName(Parcel in) {
        this.zhHantName = in.readString();
        this.englishName = in.readString();
        this.zhHansName = in.readString();
        this.atVNName = in.readString();
    }
}
