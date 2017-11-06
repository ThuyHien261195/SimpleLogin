package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.Profile;

import java.util.List;

/**
 * Created by thuyhien on 11/6/17.
 */

public interface ProfileView extends BaseView {
    void showLoading();

    void hideLoading();

    void displayProfileList(List<Profile> profileList);

    void notifyProfileDeleteSuccess(Profile profile);
}
