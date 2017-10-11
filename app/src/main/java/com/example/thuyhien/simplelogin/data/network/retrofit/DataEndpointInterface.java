package com.example.thuyhien.simplelogin.data.network.retrofit;

import com.example.thuyhien.simplelogin.model.Page;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by thuyhien on 10/11/17.
 */

public interface DataEndpointInterface {

    @GET("api/v1/pages.json")
    Call<List<Page>> getPageList(@Query("platform") String platform);
}
