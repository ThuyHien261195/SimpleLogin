package com.example.thuyhien.simplelogin.model;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MultiLangSectionName {
    String zhHantName;
    String englishName;
    String zhHansName;
    String atVNName;

    public MultiLangSectionName() {
        this.zhHantName = "";
        this.englishName = "";
        this.zhHantName = "";
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
}
