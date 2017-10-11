package com.example.thuyhien.simplelogin.model;

import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class Page {
    String id;
    MultiLangSectionName multiLangSectionName;
    List<Section> sectionList;

    public Page() {
        this.id = "";
        this.multiLangSectionName = null;
        this.sectionList = null;
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
}
