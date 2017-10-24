package com.example.thuyhien.simplelogin.data.interactor.impl;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.thuyhien.simplelogin.data.interactor.DataCache;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.AsyncTaskResult;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.utils.ImageUtils;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thuyhien on 10/12/17.
 */

public class RetrofitLoadDataInteractor implements LoadDataInteractor {

    public static final String DEFAULT_RANGE_LOAD_DATA = "1-20";

    private DataEndpointInterface dataApiService;

    private DataCache dataCache;

    public RetrofitLoadDataInteractor(DataEndpointInterface dataApiService, DataCache dataCache) {
        this.dataApiService = dataApiService;
        this.dataCache = dataCache;
    }

    @Override
    public void getPageList(final Boolean useCache, final LoadDataListener<List<Page>> listener) {
        new AsyncTask<Void, Void, AsyncTaskResult<List<Page>>>() {
            @Override
            protected AsyncTaskResult<List<Page>> doInBackground(Void... voids) {
                List<Page> pageList;
                if (useCache) {
                    pageList = dataCache.getPageList();
                    if (pageList != null && pageList.size() > 0) {
                        return new AsyncTaskResult<>(pageList);
                    }
                }

                dataCache.deleteDataInFolder();

                Call<List<Page>> call = dataApiService.getPageList();
                try {
                    Response<List<Page>> response = call.execute();
                    pageList = response.body();
                    if (response.isSuccessful() && pageList != null) {
                        dataCache.savePageList(pageList);
                        return new AsyncTaskResult<>(pageList);
                    } else {
                        return new AsyncTaskResult<>(
                                RetrofitUtils.createLoadDataException(response.errorBody()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<List<Page>> asyncTaskResult) {
                if (asyncTaskResult.getResult() != null) {
                    listener.onLoadDataSuccess(asyncTaskResult.getResult());
                } else {
                    listener.onLoadDataFail(asyncTaskResult.getException());
                }
            }
        }.execute();
    }

    @Override
    public void getFeedList(final Section section, final Boolean useCache,
                            final LoadFeedListListener listener) {
        new AsyncTask<Section, Void, AsyncTaskResult<List<MediaFeed>>>() {
            @Override
            protected AsyncTaskResult<List<MediaFeed>> doInBackground(Section... sections) {
                List<MediaFeed> mediaFeedList;
                if (useCache) {
                    mediaFeedList = dataCache.getFeedList(section);
                    if (mediaFeedList != null) {
                        return new AsyncTaskResult<>(mediaFeedList);
                    }
                }

                String rangeValue = addRangeLoadData(section.getFeedUrl());
                Call<List<MediaFeed>> call = dataApiService.getFeedList(section.getFeedUrl(), rangeValue);
                try {
                    Response<List<MediaFeed>> response = call.execute();
                    mediaFeedList = response.body();
                    if (response.isSuccessful() && mediaFeedList != null) {
                        dataCache.saveFeed(section, mediaFeedList);
                        return new AsyncTaskResult<>(mediaFeedList);
                    } else {
                        return new AsyncTaskResult<>(RetrofitUtils.createLoadDataException(response.errorBody()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<List<MediaFeed>> asyncTaskResult) {
                if (asyncTaskResult.getResult() != null) {
                    listener.onLoadDataSuccess(section, asyncTaskResult.getResult());
                } else {
                    listener.onLoadDataFail(asyncTaskResult.getException());
                }
            }
        }.execute(section);
    }

    @Override
    public void getPage(final Page page, final LoadDataListener<Page> listener) {
        new AsyncTask<Void, Void, AsyncTaskResult<Page>>() {
            @Override
            protected AsyncTaskResult<Page> doInBackground(Void... voids) {
                Call<Page> call = dataApiService.getPage(page.getId());
                try {
                    Response<Page> response = call.execute();
                    if (response.isSuccessful() && response.body() != null) {
                        dataCache.deleteFeedListFileOfOnePage(page);
                        dataCache.savePage(response.body());
                        return new AsyncTaskResult<>(response.body());
                    } else {
                        return new AsyncTaskResult<>(RetrofitUtils.createLoadDataException(response.errorBody()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new AsyncTaskResult<>(e);
                }
            }

            @Override
            protected void onPostExecute(AsyncTaskResult<Page> asyncTaskResult) {
                if (asyncTaskResult.getResult() != null) {
                    listener.onLoadDataSuccess(asyncTaskResult.getResult());
                } else {
                    listener.onLoadDataFail(asyncTaskResult.getException());
                }
            }
        }.execute();
    }

    private String addRangeLoadData(String feedUrl) {
        int pos = feedUrl.indexOf("range");
        if (pos == -1) {
            return DEFAULT_RANGE_LOAD_DATA;
        }
        return null;
    }

    @Override
    public void getPoster(final MediaImage imagePost, final LoadDataListener<Bitmap> listener) {
        Call<ResponseBody> call = dataApiService.getImagePost(imagePost.getImageUrl());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bitmap = ImageUtils.decodeBitMapFromInputStream(inputStream,
                            imagePost.getWidth(),
                            imagePost.getHeight());
                    listener.onLoadDataSuccess(bitmap);
                } else {
                    listener.onLoadDataFail(RetrofitUtils.createLoadDataException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onLoadDataFail(new Exception(t));
            }
        });
    }

//    @Override
//    public List<Section> getTotalFeedList(Section[] sections) {
//        if (sections != null) {
//            for (Section section : sections) {
//                if (!section.getType().equals("Custom layout")) {
//                    String feedUrl = addRangeLoadData(section.getFeedUrl());
//                    Call<List<MediaFeed>> call = dataApiService.getFeedList(section.getFeedUrl(), feedUrl);
//                    try {
//                        Response<List<MediaFeed>> response = call.execute();
//                        if (response.isSuccessful() && response.body() != null) {
//                            section.setFeedPostList(response.body());
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return new ArrayList<>(Arrays.asList(sections));
//        }
//        return new ArrayList<>();
//    }
}
