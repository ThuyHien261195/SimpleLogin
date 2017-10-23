package com.example.thuyhien.simplelogin.data.interactor.impl;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.thuyhien.simplelogin.data.interactor.FileInteractor;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.utils.ImageUtils;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;

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

    private FileInteractor fileInteractor;

    public RetrofitLoadDataInteractor(DataEndpointInterface dataApiService, FileInteractor fileInteractor) {
        this.dataApiService = dataApiService;
        this.fileInteractor = fileInteractor;
    }

    @Override
    public void getPageList(Boolean useCache, final LoadDataListener<List<Page>> listener) {
        if (useCache) {
            boolean loadSuccess = fileInteractor.getPageList(listener);
            if (loadSuccess) {
                return;
            }
        }

        fileInteractor.deleteDataInFolder();

        Call<List<Page>> call = dataApiService.getPageList();
        call.enqueue(new Callback<List<Page>>() {
            @Override
            public void onResponse(Call<List<Page>> call, Response<List<Page>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fileInteractor.savePageList(response.body());
                    listener.onLoadDataSuccess(response.body());
                } else {
                    listener.onLoadDataFail(RetrofitUtils.createLoadDataException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<Page>> call, Throwable t) {
                listener.onLoadDataFail(new Exception(t));
            }
        });
    }

    @Override
    public void getFeedList(final Page page, Boolean useCache,
                            final LoadFeedListListener listener) {
        if (useCache && fileInteractor.checkFeedFileListExits()) {
            if (fileInteractor.getFeedList(page, listener)) {
                return;
            }
        }

        fileInteractor.clearFeedFile(page);
        List<Section> sectionList = page.getSectionList();
        for (int i = 0; i < sectionList.size(); i++) {
            final Section section = sectionList.get(i);
            if (!section.getType().equals("Custom layout")) {
                String rangeValue = addRangeLoadData(section.getFeedUrl());
                Call<List<MediaFeed>> call = dataApiService.getFeedList(section.getFeedUrl(), rangeValue);
                call.enqueue(new Callback<List<MediaFeed>>() {
                    @Override
                    public void onResponse(Call<List<MediaFeed>> call, Response<List<MediaFeed>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fileInteractor.saveFeed(page, section, response.body());
                            listener.onLoadDataSuccess(section, response.body());
                        } else {
                            listener.onLoadDataFail(RetrofitUtils.createLoadDataException(response.errorBody()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MediaFeed>> call, Throwable t) {
                        listener.onLoadDataFail(new Exception(t));
                    }
                });
            }
        }
    }

    @Override
    public void getPage(String pageId, final LoadDataListener<Page> listener) {
        Call<Page> call = dataApiService.getPage(pageId);
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fileInteractor.savePage(response.body());
                    listener.onLoadDataSuccess(response.body());
                } else {
                    listener.onLoadDataFail(RetrofitUtils.createLoadDataException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                listener.onLoadDataFail(new Exception(t));
            }
        });
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
