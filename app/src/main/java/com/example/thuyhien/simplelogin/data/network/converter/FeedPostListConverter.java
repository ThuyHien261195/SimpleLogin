package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPostListConverter extends BaseDeserializer<List<MediaFeed>> {

    public static final String JSON_FEED_LIST_KEY = "entries";

    @Override
    public List<MediaFeed> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (checkValidJsonObject(json)) {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement jsonElement = jsonObject.get(JSON_FEED_LIST_KEY);
            if (checkValidJsonArray(jsonElement)) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                List<MediaFeed> mediaFeedList = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    mediaFeedList.add((MediaFeed) context.deserialize(jsonArray.get(i), MediaFeed.class));
                }
                return mediaFeedList;
            }
        }
        return null;
    }
}
