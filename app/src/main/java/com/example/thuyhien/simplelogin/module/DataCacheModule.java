package com.example.thuyhien.simplelogin.module;

import android.os.Environment;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.DataCache;
import com.example.thuyhien.simplelogin.data.interactor.impl.FileDataCache;
import com.google.gson.Gson;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thuyhien on 10/25/17.
 */

@Module
public class DataCacheModule {
    public static final String FOX_FOLDER_NAME = "FoxFile";
    public static final String FEED_LIST_FOLDER = File.separator + FOX_FOLDER_NAME + "/FeedListFolder";
    public static final String PAGE_FOLDER = File.separator + FOX_FOLDER_NAME + "/PageFolder";

    @Provides
    @Named("page_dir")
    public File getPageDir(FoxApplication application) {
        return getDirectory(application, PAGE_FOLDER);
    }

    @Provides
    @Named("feed_dir")
    public File getFeedDir(FoxApplication application) {
        return getDirectory(application, FEED_LIST_FOLDER);
    }

    @Provides
    public DataCache dataCache(@Named("data_gson") Gson dataGson,
                               @Named("page_dir") File pageDir,
                               @Named("feed_dir") File feedDir) {
        return new FileDataCache(dataGson, pageDir, feedDir);
    }

    public File getDirectory(FoxApplication application, String folderPath) {
        File dir;
        if (checkExternalStorageAvailable()) {
            dir = new File(application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), folderPath);
        } else {
            dir = new File(application.getFilesDir(), folderPath);
        }
        if (!dir.exists() && !dir.mkdirs()) {
            return null;
        }
        return dir;
    }

    private boolean checkExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
