package com.example.thuyhien.simplelogin.data.interactor;

import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadImageListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadPageListListener;
import com.example.thuyhien.simplelogin.model.MediaImage;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface LoadDataInteractor {
    void getPageList(final OnLoadPageListListener listener);

    void getPoster(MediaImage imagePost, final OnLoadImageListener listener);
}
