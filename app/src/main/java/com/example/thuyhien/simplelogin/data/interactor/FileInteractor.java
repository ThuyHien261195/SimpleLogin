package com.example.thuyhien.simplelogin.data.interactor;

import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/18/17.
 */

public interface FileInteractor {
    boolean getPageList(final LoadDataListener<List<Page>> listener);

    boolean getFeedList(Page page, final LoadFeedListListener listener);

    void savePageList(List<Page> pageList);

    void savePage(Page page);

    void saveFeed(Page page, Section section,
                  List<MediaFeed> mediaFeedList);

    void clearFeedFile(Page page);

    void deleteDataInFolder();

    boolean checkFeedFileListExits();
}
