package com.example.thuyhien.simplelogin.data.interactor.listener;

import android.graphics.Bitmap;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface OnLoadImageListener extends OnBaseLoadDataListener<Bitmap> {
    void onLoadDataSuccess(Bitmap bitmap);

    void onLoadDataFail(Exception ex);
}
