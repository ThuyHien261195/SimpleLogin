package com.example.thuyhien.simplelogin.data.network.converter;

import com.example.thuyhien.simplelogin.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by thuyhien on 10/6/17.
 */

public class UserConverter extends BaseDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User user = new User();
        if (checkValidJsonObject(json)) {
            JsonObject jsonObject = json.getAsJsonObject();
            user.setToken(getStringValue(jsonObject.get("token"), ""));
            user.setRefreshToken(getStringValue(jsonObject.get("refresh_token"), ""));

            JsonElement jsonElement = jsonObject.get("profiles");
            if (checkValidJsonArray(jsonElement)) {
                JsonArray profileJsonArray = jsonElement.getAsJsonArray();
                jsonObject = profileJsonArray.get(0).getAsJsonObject();
                user.setAccountEmail(getStringValue(jsonObject.get("account_email"), ""));
            }
        }
        return user;
    }
}
