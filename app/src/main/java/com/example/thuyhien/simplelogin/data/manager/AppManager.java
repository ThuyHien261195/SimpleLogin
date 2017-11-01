package com.example.thuyhien.simplelogin.data.manager;

/**
 * Created by thuyhien on 10/10/17.
 */

public interface AppManager {
    boolean isAlreadyUsedApp();

    void setAlreadyUsedApp();

    String getUsedLanguage();

    void setUsedLanguage();
}
