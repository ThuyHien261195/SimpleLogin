package com.example.thuyhien.simplelogin.presenter;

import com.example.thuyhien.simplelogin.model.Page;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface PagePresenter extends BasePresenter {
    void loadAllFeedList(Page page, boolean isRefresh);

    void loadPage(Page page);
}
