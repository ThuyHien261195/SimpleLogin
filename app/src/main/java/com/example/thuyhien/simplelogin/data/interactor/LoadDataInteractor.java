package com.example.thuyhien.simplelogin.data.interactor;

import android.graphics.Bitmap;

import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface LoadDataInteractor {
    void getPageList(Boolean useCache, final LoadDataListener<List<Page>> listener);

    void getFeedList(final Page page, Boolean useCache,
                     final LoadFeedListListener listener);

    void getPage(String pageId, final LoadDataListener<Page> listener);

    void getPoster(MediaImage imagePost, final LoadDataListener<Bitmap> listener);
}
