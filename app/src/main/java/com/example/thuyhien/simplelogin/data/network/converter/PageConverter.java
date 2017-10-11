package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.MultiLangSectionName;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PageConverter extends BaseDeserializer<Page> {


    @Override
    public Page deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Page page = null;

        if (json != null && json.isJsonObject()) {
            page = new Page();
            JsonObject jsonObject = json.getAsJsonObject();
            page.setId(getStringValue(jsonObject.get("id"), ""));

            JsonElement jsonElement = jsonObject.get("names");
            if(checkValidJsonObject(json)) {
                jsonObject = jsonElement.getAsJsonObject();
                MultiLangSectionName multiLangSectionName = new MultiLangSectionName();
                multiLangSectionName.setZhHantName(getStringValue(jsonObject.get("zh-hant"), ""));
                multiLangSectionName.setZhHansName(getStringValue(jsonObject.get("zh-hans"), ""));
                multiLangSectionName.setEnglishName(getStringValue(jsonObject.get("en"), ""));
                multiLangSectionName.setAtVNName(getStringValue(jsonObject.get("at-vn"), ""));
                page.setMultiLangSectionName(multiLangSectionName);
            }

            jsonElement = jsonObject.get("sections");
            if(checkValidJsonArray(jsonElement)) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                Type type = new TypeToken<List<Section>>(){}.getType();
                GsonBuilder gsonBuilder = new GsonBuilder()
                        .registerTypeAdapter(Section.class, new SectionConverter());
                Gson gson = gsonBuilder.create();
                List<Section> sectionList = gson.fromJson(jsonArray, type);
                page.setSectionList(sectionList);
            }
        }
        return page;
    }
}
