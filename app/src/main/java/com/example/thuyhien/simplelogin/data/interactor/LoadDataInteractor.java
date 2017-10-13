package com.example.thuyhien.simplelogin.data.interactor;

import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadImageListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadPageListListener;
import com.example.thuyhien.simplelogin.model.ImagePost;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface LoadDataInteractor {
    void getPostList(final OnLoadPageListListener listener);

    void getFeedList(String feedUrl, final OnLoadFeedListener listener);

    void getPoster(ImagePost imagePost, final OnLoadImageListener listener);
}
