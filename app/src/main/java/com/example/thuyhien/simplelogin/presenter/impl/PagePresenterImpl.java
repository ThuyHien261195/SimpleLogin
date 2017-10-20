package com.example.thuyhien.simplelogin.presenter.impl;

import com.example.thuyhien.simplelogin.data.interactor.FileInteractor;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.utils.FileProvider;
import com.example.thuyhien.simplelogin.view.PageView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public class PagePresenterImpl implements PagePresenter {

    private WeakReference<PageView> pageViewWeakReference;
    private LoadDataInteractor loadDataInteractor;
    private FileInteractor fileInteractor;

    public PagePresenterImpl(PageView pageView,
                             LoadDataInteractor loadDataInteractor,
                             FileInteractor fileInteractor) {
        this.pageViewWeakReference = new WeakReference<PageView>(pageView);
        this.loadDataInteractor = loadDataInteractor;
        this.fileInteractor = fileInteractor;
    }


    @Override
    public void loadAllFeedList(Page page, boolean isRefresh) {
        if (getPageView() != null) {
            getPageView().showLoading();
        }

        if (!isRefresh && fileInteractor.checkHasDataInFolder(FileProvider.FEED_LIST_FOLDER)) {
            loadFeedListFromFile(page);
        } else {
            loadFeedListFromServer(page);
        }
    }

    @Override
    public void loadPage(Page page) {
        loadDataInteractor.getPage(page.getId(), new LoadDataListener<Page>() {
            @Override
            public void onLoadDataSuccess(Page data) {
                if (getPageView() != null) {
                    fileInteractor.savePage(data);
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

    private void loadFeedListFromFile(final Page page) {
        fileInteractor.getFeedList(page, new LoadFeedListListener() {
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
                    loadFeedListFromServer(page);
                }
            }
        });
    }

    private void loadFeedListFromServer(final Page page) {
        // clear feed list in file
        fileInteractor.clearFile(page);

        List<Section> sectionList = page.getSectionList();
        for (int i = 0; i < sectionList.size(); i++) {
            final Section section = sectionList.get(i);
            if (!section.getType().equals("Custom layout")) {
                loadDataInteractor.getFeedList(section, new LoadFeedListListener() {
                    @Override
                    public void onLoadDataSuccess(Section section, List<MediaFeed> mediaFeedList) {
                        fileInteractor.saveFeed(page, section, mediaFeedList);
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
