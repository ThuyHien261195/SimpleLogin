package com.example.thuyhien.simplelogin.data.manager;

import com.example.thuyhien.simplelogin.model.User;

/**
 * Created by thuyhien on 10/10/17.
 */

public interface UserManager {
    void saveUser(User user);

    void clearUser();

    boolean isLoggedIn();

    String getEmail();
}
