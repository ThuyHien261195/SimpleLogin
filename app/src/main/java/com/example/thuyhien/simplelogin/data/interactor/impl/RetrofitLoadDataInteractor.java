package com.example.thuyhien.simplelogin.data.interactor.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadImageListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadPageListListener;
import com.example.thuyhien.simplelogin.model.FeedPost;
import com.example.thuyhien.simplelogin.model.Page;
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

    @Override
    public void getPostList(final OnLoadPageListListener listener) {
        String platform = "app";
        Call<List<Page>> call = FoxApplication.getInstance().getDataApiService().getPageList(platform);
        call.enqueue(new Callback<List<Page>>() {
            @Override
            public void onResponse(Call<List<Page>> call, Response<List<Page>> response) {
                if (response.isSuccessful() && response.body() != null) {
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
    public void getFeedList(String feedUrl, final OnLoadFeedListener listener) {
        Call<List<FeedPost>> call = FoxApplication.getInstance().getDataApiService().getFeedList(feedUrl);
        call.enqueue(new Callback<List<FeedPost>>() {
            @Override
            public void onResponse(Call<List<FeedPost>> call, Response<List<FeedPost>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onLoadDataSuccess(response.body());
                } else {
                    listener.onLoadDataFail(
                            RetrofitUtils.createLoadDataException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<FeedPost>> call, Throwable t) {
                listener.onLoadDataFail(new Exception(t));
            }
        });
    }

    @Override
    public void getPoster(String imageUrl, final OnLoadImageListener listener) {
        Call<ResponseBody> call = FoxApplication.getInstance().getDataApiService().getImagePost(imageUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    listener.onLoadDataSuccess(bitmap);
                } else {
                    listener.onLoadDataFail(RetrofitUtils.createAuthenException(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onLoadDataFail(new Exception(t));
            }
        });
    }
}
