package com.example.thuyhien.simplelogin.dagger.module;

import android.content.Context;
import android.os.Environment;

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
    public static final String DI_PAGE_DIR = "page_dir";
    public static final String DI_FEED_DIR = "feed_dir";

    @Provides
    @Named(DI_PAGE_DIR)
    static File getPageDir(Context application) {
        return getDirectory(application, PAGE_FOLDER);
    }

    @Provides
    @Named(DI_FEED_DIR)
    static File getFeedDir(Context application) {
        return getDirectory(application, FEED_LIST_FOLDER);
    }

    @Provides
    static DataCache dataCache(@Named(AppModule.DI_DATA_GSON) Gson dataGson,
                               @Named(DI_PAGE_DIR) File pageDir,
                               @Named(DI_FEED_DIR) File feedDir) {
        return new FileDataCache(dataGson, pageDir, feedDir);
    }

    private static File getDirectory(Context application, String folderPath) {
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

    private static boolean checkExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
