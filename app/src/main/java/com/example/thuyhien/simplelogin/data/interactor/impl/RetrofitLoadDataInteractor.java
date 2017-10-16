package com.example.thuyhien.simplelogin.data.interactor.impl;

import android.graphics.Bitmap;
import android.provider.ContactsContract;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadImageListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadPageListListener;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.model.Page;
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

    private DataEndpointInterface dataApiService;

    public RetrofitLoadDataInteractor(DataEndpointInterface dataApiService) {
        this.dataApiService = dataApiService;
    }

    @Override
    public void getPageList(final OnLoadPageListListener listener) {
        Call<List<Page>> call = dataApiService.getPageList();
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
    public void getPoster(final MediaImage imagePost, final OnLoadImageListener listener) {
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
