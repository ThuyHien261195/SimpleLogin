package com.example.thuyhien.simplelogin.dagger.module;

import com.example.thuyhien.simplelogin.data.interactor.AuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.DataCache;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitAuthenticationInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitLoadDataInteractor;
import com.example.thuyhien.simplelogin.data.network.retrofit.AuthenticationEndpointInterface;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.thuyhien.simplelogin.BuildConfig.DATA_BASE_URL;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class NetModule {
    private static final String AUTHENTICATION_BASE_URL = "http://userkit-identity-stg.ap-southeast-1.elasticbeanstalk.com/v1/";

    @Provides
    @Singleton
    static AuthenticationEndpointInterface provideAuthenApiService(@Named(AppModule.DI_AUTHEN_GSON) Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AUTHENTICATION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(AuthenticationEndpointInterface.class);
    }

    @Provides
    @Singleton
    static DataEndpointInterface provideDataApiService(@Named(AppModule.DI_DATA_GSON) Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DATA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(DataEndpointInterface.class);
    }

    @Provides
    @Singleton
    static AuthenticationInteractor provideAuthenInteractor(AuthenticationEndpointInterface authenApiService) {
        return new RetrofitAuthenticationInteractor(authenApiService);
    }

    @Provides
    static LoadDataInteractor provideLoadDataInterator(DataEndpointInterface dataApiService,
                                                       DataCache dataCache) {
        return new RetrofitLoadDataInteractor(dataApiService, dataCache);
    }
}
