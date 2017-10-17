package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.Section;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface PageView {
    void showLoading();

    void displayMediaFeedList(Section section, int position);

    void hideLoading();
}
