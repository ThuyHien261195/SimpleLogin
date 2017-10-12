package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.MultiLangSectionName;
import com.example.thuyhien.simplelogin.model.Section;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by thuyhien on 10/11/17.
 */

public class SectionConverter extends BaseDeserializer<Section> {
    @Override
    public Section deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Section section = null;
        if (checkValidJsonObject(json)) {
            section = new Section();
            JsonObject jsonObject = json.getAsJsonObject();
            section.setId(getStringValue(jsonObject.get("id"), ""));
            section.setFeedUrl(getStringValue(jsonObject.get("feed_url"), ""));
            section.setType(getStringValue(jsonObject.get("type"), ""));

            JsonElement jsonElement = jsonObject.get("names");
            if (checkValidJsonObject(jsonElement)) {
                jsonObject = jsonElement.getAsJsonObject();
                MultiLangSectionName multiLangSectionName = new MultiLangSectionName();
                multiLangSectionName.setZhHantName(getStringValue(jsonObject.get("zh-hant"), ""));
                multiLangSectionName.setZhHansName(getStringValue(jsonObject.get("zh-hans"), ""));
                multiLangSectionName.setEnglishName(getStringValue(jsonObject.get("en"), ""));
                multiLangSectionName.setAtVNName(getStringValue(jsonObject.get("at-vn"), ""));
                section.setMultiLangSectionName(multiLangSectionName);
            }
        }
        return section;
    }
}
