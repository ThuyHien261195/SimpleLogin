package com.example.thuyhien.simplelogin.data.interactor.listener;

/**
 * Created by thuyhien on 11/7/17.
 */

public interface LoadProfileListener<T> {
    void onLoadProfileSuccess(T data);

    void onLoadProfileFail(Exception ex);
}
