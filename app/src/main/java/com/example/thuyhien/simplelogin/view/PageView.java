package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface PageView extends BaseView {
    void showLoading();

    void hideLoading();

    void displayMediaFeedList(Section section, List<MediaFeed> mediaFeedList);

    void displayRefreshPage(Page page);

    void getCurrentLangCode(String langCode);
}
