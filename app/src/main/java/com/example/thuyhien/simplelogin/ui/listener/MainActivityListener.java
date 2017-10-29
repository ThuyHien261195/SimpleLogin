package com.example.thuyhien.simplelogin.ui.listener;

import android.os.Bundle;

import com.example.thuyhien.simplelogin.model.MediaFeed;

/**
 * Created by thuyhien on 10/23/17.
 */

public interface MainActivityListener {
    void onChangeTitlePage(String pageId, String title);

    void onShowMediaFeedDetail(Bundle feedBundle);
}
