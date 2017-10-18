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
import java.util.Map;

/**
 * Created by thuyhien on 10/12/17.
 */

public class PagePresenterImpl implements PagePresenter {

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
        for (int i = 0; i < sectionList.size(); i++) {
            final Section section = sectionList.get(i);
            if (!section.getType().equals("Custom layout")) {
                loadDataInteractor.getFeedList(section, new LoadFeedListListener() {
                    @Override
                    public void onLoadDataSuccess(Section section, List<MediaFeed> mediaFeedList) {
                        if (getPageView() != null) {
                            getPageView().hideLoading();
                            getPageView().displayMediaFeedList(section, mediaFeedList);
                        }
                    }

                    @Override
                    public void onLoadDataFail(Exception ex) {
                        // TODO
                    }
                });
            }
        }
    }

    @Override
    public void loadPage(Page page) {
        loadDataInteractor.getPage(page.getId(), new LoadDataListener<Page>() {
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

    private PageView getPageView() {
        return pageViewWeakReference.get();
    }

//    private void getFeedListEachSection(List<Section> sectionList) {
//        DownloadFeedTask downloadFeedTask = new DownloadFeedTask(loadDataInteractor, this);
//        Section[] sections = sectionList.toArray(new Section[sectionList.size()]);
//        downloadFeedTask.execute(sections);
//    }
}
