package com.example.thuyhien.simplelogin.data.interactor;

import android.graphics.Bitmap;

import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface LoadDataInteractor {
    void getPageList(final Boolean useCache, final LoadDataListener<List<Page>> listener);

    void getFeedList(final Section section, final Boolean useCache,
                     final LoadFeedListListener listener);

    void getPage(final Page page, final LoadDataListener<Page> listener);
}
