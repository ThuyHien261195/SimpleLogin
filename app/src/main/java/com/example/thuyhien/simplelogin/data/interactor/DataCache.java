package com.example.thuyhien.simplelogin.data.interactor;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/18/17.
 */

public interface DataCache {
    List<Page> getPageList();

    List<MediaFeed> getFeedList(Section section);

    void savePageList(List<Page> pageList);

    void savePage(Page page);

    void saveFeed(Section section, List<MediaFeed> mediaFeedList);

    void deleteFeedListFileOfOnePage(Page page);

    void deleteDataInFolder();
}
