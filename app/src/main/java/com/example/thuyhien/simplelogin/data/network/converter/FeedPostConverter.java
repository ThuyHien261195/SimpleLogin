package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class FeedPostConverter extends BaseDeserializer<MediaFeed> {
    @Override
    public MediaFeed deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        MediaFeed feedPost = null;
        if (checkValidJsonObject(json)) {
            feedPost = new MediaFeed();
            JsonObject jsonObject = json.getAsJsonObject();
            feedPost.setId(getStringValue(jsonObject.get("id"), ""));
            feedPost.setGuid(getStringValue(jsonObject.get("guid"), ""));
            feedPost.setTitle(getStringValue(jsonObject.get("title"), ""));
            feedPost.setDescription(getStringValue(jsonObject.get("description"), ""));
            List<MediaImage> thumbnails = getImagePost(jsonObject.get("thumbnails"), context);
            feedPost.setThumbnails(thumbnails);
        }
        return feedPost;
    }

    private List<MediaImage> getImagePost(JsonElement jsonElement, JsonDeserializationContext context) {
        Type thumbnailType;
        if (checkValidJsonObject(jsonElement)) {
            thumbnailType = new TypeToken<HashMap<String, MediaImage>>() {
            }.getType();
            HashMap<String, MediaImage> thumbnails = context.deserialize(jsonElement, thumbnailType);
            if (thumbnails != null && thumbnails.size() != 0) {
                return new ArrayList<MediaImage>(thumbnails.values());
            }

        } else if (checkValidJsonArray(jsonElement)) {;
            thumbnailType = new TypeToken<List<MediaImage>>() {
            }.getType();
            List<MediaImage> imagePostList = context.deserialize(jsonElement, thumbnailType);
            if (imagePostList != null && imagePostList.size() != 0) {
                return imagePostList;
            }
        }
        return new ArrayList<>();
    }
}
