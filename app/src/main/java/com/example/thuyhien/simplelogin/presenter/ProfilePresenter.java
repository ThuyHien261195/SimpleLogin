package com.example.thuyhien.simplelogin.presenter;

import com.example.thuyhien.simplelogin.model.Profile;

import java.util.List;

/**
 * Created by thuyhien on 11/6/17.
 */

public interface ProfilePresenter {
    void loadProfileList();

    void deleteProfileList(List<Profile> profileList);
}
