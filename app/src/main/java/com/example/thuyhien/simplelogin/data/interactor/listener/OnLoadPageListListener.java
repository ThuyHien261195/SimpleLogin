package com.example.thuyhien.simplelogin.data.interactor.listener;

import com.example.thuyhien.simplelogin.model.Page;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface OnLoadPageListListener extends OnBaseLoadDataListener<List<Page>> {
    void onLoadDataSuccess(List<Page> pageList);

    void onLoadDataFail(Exception ex);
}
