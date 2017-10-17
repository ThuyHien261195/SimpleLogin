package com.example.thuyhien.simplelogin.data.interactor;

import android.graphics.Bitmap;

import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListener;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface LoadDataInteractor {
    void getPageList(final LoadDataListener<List<Page>> listener);

    void getFeedList(List<Section> sectionList, final LoadFeedListener listener);

    void getPoster(MediaImage imagePost, final LoadDataListener<Bitmap> listener);

    List<Section> getTotalFeedList(Section[] sections);
}
