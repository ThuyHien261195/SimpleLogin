package com.example.thuyhien.simplelogin.data.database.model.converter;

import com.example.thuyhien.simplelogin.data.database.model.User;
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

public class UserConverter implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User user = new User();
        if (json != null && json.isJsonObject()) {

            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement jsonElement = jsonObject.get("token");
            if (jsonElement != null) {
                user.setToken(jsonElement.getAsString());
            }

            jsonElement = jsonObject.get("refresh_token");
            if (jsonElement != null) {
                user.setRefreshToken(jsonElement.getAsString());
            }

            jsonElement = jsonObject.get("profiles");
            if (jsonElement != null && jsonElement.isJsonArray()) {
                JsonArray profileJsonArray = jsonElement.getAsJsonArray();
                jsonObject = profileJsonArray.get(0).getAsJsonObject();
                user.setAccountEmail(jsonObject.get("account_email").getAsString());
            }
        }
        return user;
    }
}
