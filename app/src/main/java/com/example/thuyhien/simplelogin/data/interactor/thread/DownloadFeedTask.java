package com.example.thuyhien.simplelogin.data.interactor.thread;

import android.os.AsyncTask;

import com.example.thuyhien.simplelogin.data.interactor.listener.OnLoadFeedListener;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by thuyhien on 10/16/17.
 */

public class DownloadFeedTask extends AsyncTask<Section, Void, List<Section>> {

    private DataEndpointInterface dataApiService;
    private OnLoadFeedListener listener;

    public DownloadFeedTask(DataEndpointInterface dataApiService, OnLoadFeedListener listener) {
        this.dataApiService = dataApiService;
        this.listener = listener;
    }

    @Override
    protected List<Section> doInBackground(Section... sections) {
        for (Section section : sections) {
            Call<List<MediaFeed>> call = dataApiService.getFeedList(section.getFeedUrl());
            try {
                Response<List<MediaFeed>> response = call.execute();
                if(response.isSuccessful() && response.body() != null) {
                    section.setFeedPostList(response.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<Section>(Arrays.asList(sections));
    }

    @Override
    protected void onPostExecute(List<Section> sectionList) {
        listener.onLoadDataSuccess(sectionList);
    }
}
