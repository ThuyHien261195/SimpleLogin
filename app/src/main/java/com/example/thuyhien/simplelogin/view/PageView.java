package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;
import java.util.Map;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface PageView {
    void showLoading();

    void hideLoading();

    void displayMediaFeedList(Map<Section, List<MediaFeed>> mediaFeedList);

    void displayRefreshPage(Page page);

    void showErrorMessage(Exception ex);
}
