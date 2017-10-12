package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PageListConverter extends BaseDeserializer<List<Page>> {
    @Override
    public List<Page> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (checkValidJsonArray(json)) {
            JsonArray jsonArray = json.getAsJsonArray();
            Type type = new TypeToken<List<Page>>() {
            }.getType();
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .registerTypeAdapter(Page.class, new PageConverter());
            Gson gson = gsonBuilder.create();
            return gson.fromJson(jsonArray, type);
        }
        return null;
    }
}
