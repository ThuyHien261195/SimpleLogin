package com.example.thuyhien.simplelogin.data.interactor.listener;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/18/17.
 */

public interface LoadFeedListListener {
    void onLoadDataSuccess(Section section, List<MediaFeed> mediaFeedList);

    void onLoadDataFail(Exception ex);
}
