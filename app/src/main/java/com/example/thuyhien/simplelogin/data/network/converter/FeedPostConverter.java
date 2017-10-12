package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.FeedPost;
import com.example.thuyhien.simplelogin.model.ImagePost;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPostConverter extends BaseDeserializer<FeedPost> {
    @Override
    public FeedPost deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FeedPost feedPost = null;
        if (checkValidJsonObject(json)) {
            feedPost = new FeedPost();
            JsonObject jsonObject = json.getAsJsonObject();
            feedPost.setId(getStringValue(jsonObject.get("id"), ""));
            feedPost.setGuid(getStringValue(jsonObject.get("guid"), ""));
            feedPost.setTitle(getStringValue(jsonObject.get("title"), ""));
            feedPost.setDescription(getStringValue(jsonObject.get("description"), ""));

            JsonElement jsonElement = jsonObject.get("thumbnails");
            if (checkValidJsonObject(jsonElement)) {
                jsonObject = jsonElement.getAsJsonObject();
                jsonElement = jsonObject.get("Default-464x633");
                if (checkValidJsonObject(jsonElement)) {
                    jsonObject = jsonElement.getAsJsonObject();
                    ImagePost imagePost = new ImagePost();
                    imagePost.setImageUrl(getStringValue(jsonObject.get("url"), ""));
                    imagePost.setImageTitle(getStringValue(jsonObject.get("title"), ""));
                    feedPost.setPoster(imagePost);
                }
            }
        }
        return feedPost;
    }
}
