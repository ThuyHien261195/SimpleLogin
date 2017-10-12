package com.example.thuyhien.simplelogin.data.interactor.listener;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface OnBaseLoadDataListener<T> {
    void onLoadDataSuccess(T data);

    void onLoadDataFail(Exception ex);
}
