package com.example.thuyhien.simplelogin.presenter.impl;

import android.util.Log;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.model.FeedPost;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.view.PageView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public class PagePresenterImpl implements PagePresenter, OnLoadFeedListener {

    private WeakReference<PageView> pageViewWeakReference;
    private LoadDataInteractor loadDataInteractor;
    private List<Section> sectionList;
    private int position;

    public PagePresenterImpl(PageView pageView, LoadDataInteractor loadDataInteractor) {
        this.pageViewWeakReference = new WeakReference<PageView>(pageView);
        this.loadDataInteractor = loadDataInteractor;
    }

    @Override
    public void loadAllFeedList(List<Section> sectionList) {
        this.sectionList = sectionList;
        position = 0;
        getFeedListEachSection(position);
    }

    @Override
    public void onLoadDataSuccess(List<FeedPost> feedPostList) {
        if (sectionList != null) {
            sectionList.get(position).setFeedPostList(feedPostList);
            position += 1;
            getFeedListEachSection(position);
        }
    }

    @Override
    public void onLoadDataFail(Exception ex) {
        if (sectionList != null) {
            position += 1;
            getFeedListEachSection(position);
        }
    }

    private void getFeedListEachSection(int position) {
        if (position == sectionList.size() && getPageView() != null) {
            getPageView().showAllFeedList(sectionList);
            sectionList = null;
        }

        if (sectionList != null) {
            Section section = sectionList.get(position);
            this.loadDataInteractor.getFeedList(section.getFeedUrl(), this);
        }
    }

    private PageView getPageView() {
        return pageViewWeakReference.get();
    }
}
