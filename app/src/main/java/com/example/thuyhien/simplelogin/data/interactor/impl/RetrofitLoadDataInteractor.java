package com.example.thuyhien.simplelogin.data.interactor.impl;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.thuyhien.simplelogin.data.interactor.DataCache;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by thuyhien on 10/12/17.
 */

public class RetrofitLoadDataInteractor implements LoadDataInteractor {

    private static final String DEFAULT_RANGE_LOAD_DATA = "1-20";

    private DataEndpointInterface dataApiService;

    private DataCache dataCache;

    public RetrofitLoadDataInteractor(DataEndpointInterface dataApiService, DataCache dataCache) {
        this.dataApiService = dataApiService;
        this.dataCache = dataCache;
    }

    @Override
    public void getPageList(final Boolean useCache, final LoadDataListener<List<Page>> listener) {
        new AsyncTask<Void, Void, Pair<List<Page>, Exception>>() {
            @Override
            protected Pair<List<Page>, Exception> doInBackground(Void... voids) {
                List<Page> pageList;
                if (useCache) {
                    pageList = dataCache.getPageList();
                    if (pageList != null && pageList.size() > 0) {
                        return new Pair<>(pageList, null);
                    }
                }

                dataCache.clear();

                Call<List<Page>> call = dataApiService.getPageList();
                try {
                    Response<List<Page>> response = call.execute();

                    if (response.isSuccessful()) {
                        pageList = response.body();
                        dataCache.savePageList(pageList);
                        return new Pair<>(pageList, null);
                    } else {
                        return new Pair<>(null,
                                RetrofitUtils.createLoadDataException(response.errorBody()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new Pair<>(null, new Exception(e));
                }
            }

            @Override
            protected void onPostExecute(Pair<List<Page>, Exception> pair) {
                if (pair.first != null) {
                    listener.onLoadDataSuccess(pair.first);
                } else {
                    listener.onLoadDataFail(pair.second);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void getFeedList(final Section section, final Boolean useCache,
                            final LoadFeedListListener listener) {
        new AsyncTask<Section, Void, Pair<List<MediaFeed>, Exception>>() {
            @Override
            protected Pair<List<MediaFeed>, Exception> doInBackground(Section... sections) {
                List<MediaFeed> mediaFeedList;
                if (useCache) {
                    mediaFeedList = dataCache.getFeedList(section);
                    if (mediaFeedList != null) {
                        return new Pair<>(mediaFeedList, null);
                    }
                }

                String rangeValue = addRangeLoadData(section.getFeedUrl());
                Call<List<MediaFeed>> call = dataApiService.getFeedList(section.getFeedUrl(), rangeValue);
                try {
                    Response<List<MediaFeed>> response = call.execute();
                    if (response.isSuccessful()) {
                        mediaFeedList = response.body();
                        dataCache.saveFeed(section, mediaFeedList);
                        return new Pair<>(mediaFeedList, null);
                    } else {
                        return new Pair<>(null,
                                RetrofitUtils.createLoadDataException(response.errorBody()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new Pair<>(null, new Exception(e));
                }
            }

            @Override
            protected void onPostExecute(Pair<List<MediaFeed>, Exception> pair) {
                if (pair.first != null) {
                    listener.onLoadFeedListSuccess(section, pair.first);
                } else {
                    listener.onLoadFeedListFail(pair.second);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, section);
    }

    @Override
    public void getPage(final Page page, final LoadDataListener<Page> listener) {
        new AsyncTask<Void, Void, Pair<Page, Exception>>() {
            @Override
            protected Pair<Page, Exception> doInBackground(Void... voids) {
                Call<Page> call = dataApiService.getPage(page.getId());
                try {
                    Response<Page> response = call.execute();
                    if (response.isSuccessful() && response.body() != null) {
                        dataCache.deleteFeedListFileOfOnePage(page);
                        dataCache.savePage(response.body());
                        return new Pair<>(response.body(), null);
                    } else {
                        return new Pair<>(null,
                                RetrofitUtils.createLoadDataException(response.errorBody()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new Pair<>(null, new Exception(e));
                }
            }

            @Override
            protected void onPostExecute(Pair<Page, Exception> pair) {
                if (pair.first != null) {
                    listener.onLoadDataSuccess(pair.first);
                } else {
                    listener.onLoadDataFail(pair.second);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private String addRangeLoadData(String feedUrl) {
        int pos = feedUrl.indexOf("range");
        if (pos == -1) {
            return DEFAULT_RANGE_LOAD_DATA;
        }
        return null;
    }
}
