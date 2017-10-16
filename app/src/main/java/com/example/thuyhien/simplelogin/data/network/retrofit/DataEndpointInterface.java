package com.example.thuyhien.simplelogin.data.network.retrofit;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by thuyhien on 10/11/17.
 */

public interface DataEndpointInterface {

    @GET("api/v1/pages.json?platform=app")
    Call<List<Page>> getPageList();

    @GET()
    Call<List<MediaFeed>> getFeedList(@Url String feedUrl);

    @GET()
    Call<ResponseBody> getImagePost(@Url String imageUrl);
}
