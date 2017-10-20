package com.example.thuyhien.simplelogin.data.network.converter;

import android.util.Pair;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.utils.FileProvider;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by thuyhien on 10/19/17.
 */

public class FileFeedListConverter extends BaseDeserializer<Pair<String, List<MediaFeed>>> {
    @Override
    public Pair<String, List<MediaFeed>> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (checkValidJsonObject(json)) {
            JsonObject jsonObject = json.getAsJsonObject();
            String id = getStringValue(jsonObject.get(FileProvider.JSON_SECTION_ID_KEY), "");

            Type type = new TypeToken<List<MediaFeed>>() {
            }.getType();
            List<MediaFeed> mediaFeedList = FoxApplication.getInstance().getDataGson().fromJson(json, type);
            return new Pair<>(id, mediaFeedList);
        }
        return null;
    }
}
