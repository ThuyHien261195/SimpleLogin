package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.ImagePost;
import com.example.thuyhien.simplelogin.utils.ImageUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by thuyhien on 10/13/17.
 */

public class ImagePostConverter extends BaseDeserializer<ImagePost> {
    @Override
    public ImagePost deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ImagePost imagePost = null;
        if (checkValidJsonObject(json)) {
            JsonObject jsonObject = json.getAsJsonObject();
            imagePost = new ImagePost();
            imagePost.setImageUrl(getStringValue(jsonObject.get("url"), ""));
            imagePost.setImageTitle(getStringValue(jsonObject.get("title"), ""));
            imagePost.setWidth(getIntValue(jsonObject.get("width"), ImageUtils.REQUEST_WIDTH));
            imagePost.setHeight(getIntValue(jsonObject.get("height"), ImageUtils.REQUEST_HEIGHT));
        }
        return imagePost;
    }
}
