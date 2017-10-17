package com.example.thuyhien.simplelogin.data.interactor.listener;

import com.example.thuyhien.simplelogin.model.Section;

/**
 * Created by thuyhien on 10/17/17.
 */

public interface LoadFeedListener extends LoadDataListener<Section> {
    void onLoadDataSuccess(Section section, int position);
}
