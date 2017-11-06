package com.example.thuyhien.simplelogin.data.interactor.listener;

import com.example.thuyhien.simplelogin.model.Profile;

/**
 * Created by thuyhien on 11/3/17.
 */

public interface DeleteProfileListener {
    void onDeleteProfileSuccess(Profile profile);

    void onDeleteProfileFail(Exception ex);
}
