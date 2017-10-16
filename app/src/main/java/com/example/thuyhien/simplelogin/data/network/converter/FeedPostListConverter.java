package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPostListConverter extends BaseDeserializer<List<MediaFeed>> {
    @Override
    public List<MediaFeed> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (checkValidJsonObject(json)) {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement jsonElement = jsonObject.get("entries");
            if (checkValidJsonArray(jsonElement)) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                Type type = new TypeToken<List<MediaFeed>>() {
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder()
                        .registerTypeAdapter(MediaFeed.class, new FeedPostConverter());
                Gson gson = gsonBuilder.create();
                return gson.fromJson(jsonArray, type);
            }
        }
        return null;
    }
}
