package com.example.thuyhien.simplelogin.async;

import android.os.AsyncTask;

import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/16/17.
 */

public class DownloadFeedTask extends AsyncTask<Section, Void, List<Section>> {

    private LoadDataListener<List<Section>> listener;
    private LoadDataInteractor loadDataInteractor;

    public DownloadFeedTask(LoadDataInteractor loadDataInteractor, LoadDataListener<List<Section>> listener) {
        this.loadDataInteractor = loadDataInteractor;
        this.listener = listener;
    }

    @Override
    protected List<Section> doInBackground(Section... sections) {
        if (sections != null) {
            return loadDataInteractor.getFeedList(sections);
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Section> sectionList) {
        listener.onLoadDataSuccess(sectionList);
    }
}
