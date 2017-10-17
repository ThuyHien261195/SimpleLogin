package com.example.thuyhien.simplelogin.data.interactor;

import android.graphics.Bitmap;

import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;
import java.util.Map;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface LoadDataInteractor {
    void getPageList(final LoadDataListener<List<Page>> listener);

    void getFeedList(Section section, final LoadDataListener<Map<Section, List<MediaFeed>>> listener);

    void getPoster(MediaImage imagePost, final LoadDataListener<Bitmap> listener);

    void getPage(String pageId, final LoadDataListener<Page> listener);
}
