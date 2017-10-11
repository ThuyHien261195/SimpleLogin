package com.example.thuyhien.simplelogin.data.network.converter;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by thuyhien on 10/11/17.
 */

public abstract class BaseDeserializer<T> implements JsonDeserializer<T> {

    protected boolean getBooleanValue(JsonElement jsonElement, boolean defaultValue) {
        boolean returnedValue = defaultValue;
        if(jsonElement != null && !jsonElement.isJsonNull()) {
            returnedValue = jsonElement.getAsBoolean();
        }
        return returnedValue;
    }

    protected int getIntValue(JsonElement jsonElement, int defaultValue) {
        int returnedValue = defaultValue;
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            returnedValue = jsonElement.getAsInt();
        }
        return returnedValue;
    }

    public long getLongValue(JsonElement jsonElement, long defaultValue) {
        long returnedValue = defaultValue;
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            returnedValue = jsonElement.getAsLong();
        }
        return returnedValue;
    }

    protected double getDoubleValue(JsonElement jsonElement, double defaultValue) {
        double returnedValue = defaultValue;
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            returnedValue = jsonElement.getAsDouble();
        }
        return returnedValue;
    }

    protected String getStringValue(JsonElement jsonElement, String defaultValue) {
        String returnedValue = defaultValue;
        if(jsonElement != null && !jsonElement.isJsonNull()) {
            returnedValue = jsonElement.getAsString();
        }
        return returnedValue;
    }

    protected boolean checkValidJsonObject(JsonElement jsonElement) {
        return jsonElement != null && jsonElement.isJsonObject();
    }

    protected boolean checkValidJsonArray(JsonElement jsonElement) {
        return jsonElement != null && jsonElement.isJsonArray();
    }
}
