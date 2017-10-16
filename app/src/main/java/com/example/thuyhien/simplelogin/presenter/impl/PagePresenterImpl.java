package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.data.interactor.thread.DownloadFeedTask;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.MediaFeed;
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
    private DataEndpointInterface dataApiService;

    public PagePresenterImpl(PageView pageView,
                             DataEndpointInterface dataApiService) {
        this.pageViewWeakReference = new WeakReference<PageView>(pageView);
        this.dataApiService = dataApiService;
    }

    @Override
    public void loadAllFeedList(List<Section> sectionList) {
        if(getPageView() != null) {
            getPageView().showLoading();
        }
        getFeedListEachSection(sectionList);
    }

    @Override
    public void onLoadDataSuccess(List<Section> sectionList) {
        if (getPageView() != null) {
            getPageView().hideLoading();
            getPageView().showAllFeedList(sectionList);
        }
    }

    private void getFeedListEachSection(List<Section> sectionList) {
        DownloadFeedTask downloadFeedTask = new DownloadFeedTask(dataApiService, this);
        Section[] sections = sectionList.toArray(new Section[sectionList.size()]);
        downloadFeedTask.execute(sections);
    }

    private PageView getPageView() {
        return pageViewWeakReference.get();
    }
}
