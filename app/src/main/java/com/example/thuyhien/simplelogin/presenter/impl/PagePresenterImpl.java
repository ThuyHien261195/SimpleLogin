package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListener;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.view.PageView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public class PagePresenterImpl implements PagePresenter, LoadFeedListener {

    private WeakReference<PageView> pageViewWeakReference;
    private LoadDataInteractor loadDataInteractor;

    public PagePresenterImpl(PageView pageView, LoadDataInteractor loadDataInteractor) {
        this.pageViewWeakReference = new WeakReference<PageView>(pageView);
        this.loadDataInteractor = loadDataInteractor;
    }

    @Override
    public void loadAllFeedList(List<Section> sectionList) {
        if (getPageView() != null) {
            getPageView().showLoading();
        }
        loadDataInteractor.getFeedList(sectionList, this);
    }

    @Override
    public void onLoadDataSuccess(Section section, int position) {
        if (getPageView() != null) {
            getPageView().hideLoading();
            getPageView().displayMediaFeedList(section, position);
        }
    }

    @Override
    public void onLoadDataSuccess(Section data) {

    }

    @Override
    public void onLoadDataFail(Exception ex) {
        ex.printStackTrace();
    }

    private PageView getPageView() {
        return pageViewWeakReference.get();
    }

//    private void getFeedListEachSection(List<Section> sectionList) {
//        DownloadFeedTask downloadFeedTask = new DownloadFeedTask(loadDataInteractor, this);
//        Section[] sections = sectionList.toArray(new Section[sectionList.size()]);
//        downloadFeedTask.execute(sections);
//    }
}
