package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.view.PageView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public class PagePresenterImpl implements PagePresenter {

    private WeakReference<PageView> pageViewWeakReference;
    private LoadDataInteractor loadDataInteractor;

    public PagePresenterImpl(PageView pageView,
                             LoadDataInteractor loadDataInteractor) {
        this.pageViewWeakReference = new WeakReference<PageView>(pageView);
        this.loadDataInteractor = loadDataInteractor;
    }


    @Override
    public void loadAllFeedList(Page page, boolean isRefresh) {
        if (getPageView() != null && !isRefresh) {
            getPageView().showLoading();
        }

        if (!isRefresh) {
            loadFeedList(page, true);
        } else {
            loadFeedList(page, false);
        }
    }

    @Override
    public void loadPage(Page page) {
        loadDataInteractor.getPage(page, new LoadDataListener<Page>() {
            @Override
            public void onLoadDataSuccess(Page data) {
                if (getPageView() != null) {
                    getPageView().displayRefreshPage(data);
                }
            }

            @Override
            public void onLoadDataFail(Exception ex) {
                if (getPageView() != null) {
                    getPageView().showErrorMessage(ex);
                }
            }
        });
    }

    private void loadFeedList(Page page, boolean useCache) {
        List<Section> sectionList = page.getSectionList();
        for (Section section : sectionList) {
            if (!section.getType().equals("Custom layout")) {
                loadDataInteractor.getFeedList(section, useCache, new LoadFeedListListener() {
                    @Override
                    public void onLoadDataSuccess(Section section, List<MediaFeed> mediaFeedList) {
                        if (getPageView() != null) {

                            getPageView().hideLoading();

                            getPageView().displayMediaFeedList(section, mediaFeedList);
                        }
                    }

                    @Override
                    public void onLoadDataFail(Exception ex) {
                        if (getPageView() != null) {
                            getPageView().hideLoading();
                        }
                    }
                });
            }
        }
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
