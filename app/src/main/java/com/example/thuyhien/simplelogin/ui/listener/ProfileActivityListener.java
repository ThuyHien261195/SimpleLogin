package com.example.thuyhien.simplelogin.ui.listener;

import com.example.thuyhien.simplelogin.model.Profile;

/**
 * Created by thuyhien on 11/6/17.
 */

public interface ProfileActivityListener {
    void enableDeleteProfileMode(Profile profile);

    void updateSelectDeletedProfile(Profile profile);

    void openAddProfileScreen();
}
