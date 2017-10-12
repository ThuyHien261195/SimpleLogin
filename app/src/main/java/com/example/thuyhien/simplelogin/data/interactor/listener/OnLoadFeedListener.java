package com.example.thuyhien.simplelogin.data.interactor.listener;

import com.example.thuyhien.simplelogin.model.FeedPost;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface OnLoadFeedListener extends OnBaseLoadDataListener<List<FeedPost>> {
    void onLoadDataSuccess(List<FeedPost> feedPostList);

    void onLoadDataFail(Exception ex);
}
