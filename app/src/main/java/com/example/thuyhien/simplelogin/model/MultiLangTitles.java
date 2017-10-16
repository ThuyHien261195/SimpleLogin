package com.example.thuyhien.simplelogin.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by thuyhien on 10/11/17.
 */

public class MultiLangTitles extends HashMap<String, String> implements Serializable {

    public String getTitle(String key) {
        return this.get(key);
    }
}
