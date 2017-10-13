package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.FeedPost;
import com.example.thuyhien.simplelogin.model.ImagePost;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            ImagePost imagePost = getImagePost(jsonObject.get("thumbnails"));
            if (imagePost != null) {
                feedPost.setPoster(imagePost);
            }
        }
        return feedPost;
    }

    private ImagePost getImagePost(JsonElement jsonElement) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(ImagePost.class, new ImagePostConverter());
        Gson gson = gsonBuilder.create();
        Type thumbnailType;
        if (checkValidJsonObject(jsonElement)) {
            thumbnailType = new TypeToken<HashMap<String, ImagePost>>() {
            }.getType();
            HashMap<String, ImagePost> thumbnails = gson.fromJson(jsonElement, thumbnailType);
            if (thumbnails != null && thumbnails.size() != 0) {
                Map.Entry<String, ImagePost> thumbnailsEntry = thumbnails.entrySet().iterator().next();
                return thumbnailsEntry.getValue();
            }
        } else if (checkValidJsonArray(jsonElement)) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            thumbnailType = new TypeToken<List<ImagePost>>() {
            }.getType();
            List<ImagePost> imagePostList = gson.fromJson(jsonArray, thumbnailType);
            if (imagePostList != null && imagePostList.size() != 0) {
                return imagePostList.get(0);
            }
        }
        return null;
    }
}
