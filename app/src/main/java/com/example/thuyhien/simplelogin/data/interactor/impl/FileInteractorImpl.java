package com.example.thuyhien.simplelogin.data.interactor.impl;

import android.util.Log;
import android.util.Pair;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.file.exception.FileException;
import com.example.thuyhien.simplelogin.data.interactor.FileInteractor;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadDataListener;
import com.example.thuyhien.simplelogin.data.interactor.listener.LoadFeedListListener;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.utils.FileProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/18/17.
 */

public class FileInteractorImpl implements FileInteractor {

    private Gson gson;

    public FileInteractorImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void getPageList(final LoadDataListener<List<Page>> listener) {
        File[] files = FileProvider.getFileList(FileProvider.PAGE_FOLDER);
        if (files != null && files.length > 0) {
            List<Page> pageList = new ArrayList<>();
            for (File file : files) {
                try {
                    FileInputStream fileInputStream = FileProvider.openExistedFileInputStream(file);
                    if (fileInputStream != null) {
                        Page page = readPage(fileInputStream);
                        if (page != null) {
                            pageList.add(page);
                            Log.e("getPageList read one", "Success"); // Done
                        }
                        fileInputStream.close();
                    } else {
                        Log.e("getPageList read one", "Null fileInputStream");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("getPageList read one", e.getMessage());
                }
            }
            listener.onLoadDataSuccess(pageList);
            Log.e("getPageList all", "Success"); // Done
        } else {
            listener.onLoadDataSuccess(null);
            Log.e("getPageList all", "Null page file list"); // Done

        }
    }

    @Override
    public void getPage(String fileName, final LoadDataListener<Page> listener) {
        try {
            FileInputStream fileInputStream = FileProvider.createFileInputStream(FileProvider.PAGE_FOLDER, fileName);
            Page page = readPage(fileInputStream);
            listener.onLoadDataSuccess(page);
            fileInputStream.close();
            Log.e("getPage", "Success");
        } catch (IOException e) {
            Log.e("getPage", e.getMessage());
            listener.onLoadDataFail(e);
        }
    }

    @Override
    public void savePageList(List<Page> pageList) {
        for (Page page : pageList) {
            try {
                writePage(page, FileProvider.BASE_PAGE_FILE_NAME + "_" + page.getId());
                Log.e("writePageList", "Success");  // Done
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("savePageList", "Fail " + e.getMessage());
            }
        }
    }

    @Override
    public void savePage(Page page) {
        if (page != null) {
            try {
                writePage(page, FileProvider.BASE_PAGE_FILE_NAME + "_" + page.getId());
                Log.e("writePage", "Success");  // Done
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("savePage", e.getMessage());
            }
        }
    }

    @Override
    public void getFeedList(Page page, LoadFeedListListener listener) {
        String fileName = FileProvider.BASE_FEED_FILE_NAME + "_" + page.getId();
        try {
            FileInputStream fileInputStream =
                    FileProvider.createFileInputStream(FileProvider.FEED_LIST_FOLDER, fileName);
            if (fileInputStream != null) {
                readFeedList(page, listener, fileInputStream);
                fileInputStream.close();
            } else {
                Log.e("getFeedList", "Null fileInputStream");  // Done
                listener.onLoadDataFail(new FileException(FoxApplication.getInstance().getString(R.string.error_open_file)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("getFeedList", e.getMessage());
            listener.onLoadDataFail(e);
        }
    }

    @Override
    public void saveFeed(Page page, Section section,
                         List<MediaFeed> mediaFeedList) {
        String fileName = FileProvider.BASE_FEED_FILE_NAME + "_" + page.getId();
        try {
            String feedListOfSection = convertFeedListToString(section, mediaFeedList);
            FileProvider.writeData(FileProvider.FEED_LIST_FOLDER, fileName, true, feedListOfSection);
            Log.e("saveFeed", "Success");  // Done
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("saveFeed", "Fail " + e.getMessage());
        }
    }

    @Override
    public void clearFile(Page page) {
        String fileName = FileProvider.BASE_FEED_FILE_NAME + page.getId();
        try {
            FileProvider.clearDataInFile(FileProvider.FEED_LIST_FOLDER, fileName);
            Log.e("clearFile", "Success");  // Done
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("clearFile", e.getMessage());
        }
    }

    @Override
    public boolean checkHasDataInFolder(String folder) {
        File[] files = FileProvider.getFileList(folder);
        return files != null && files.length > 0;
    }

    private void readFeedList(Page page, LoadFeedListListener listener, FileInputStream fileInputStream) throws IOException {
        List<Section> sectionList = page.getSectionList();
        String line = "";
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        if (bufferedReader.ready()) {
            while ((line = bufferedReader.readLine()) != null) {
                Type feedListOfEachSectionType = new TypeToken<Pair<String, List<MediaFeed>>>() {
                }.getType();
                Pair<String, List<MediaFeed>> feedListOfEachSection =
                        gson.fromJson(line, feedListOfEachSectionType);
                if (feedListOfEachSection != null) {
                    int pos = sectionList.indexOf(new Section(feedListOfEachSection.first));
                    if (pos != -1) {
                        Log.e("getFeedList", "Success One feed " + sectionList.get(pos).getId() + "Page " + page.getId());  // Done
                        listener.onLoadDataSuccess(sectionList.get(pos), feedListOfEachSection.second);
                    }
                }
            }
            Log.e("getFeedList", "Success"); // Done
        } else {
            Log.e("getFeedList", "Fail buffer empty"); // Done
            listener.onLoadDataFail(new FileException(FoxApplication.getInstance().getString(R.string.error_empty_file)));
        }
        bufferedReader.close();
    }

    private Page readPage(FileInputStream fileInputStream) throws IOException {
        if (fileInputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            if ((line = bufferedReader.readLine()) != null) {
                return gson.fromJson(line, Page.class);
            }
        }
        return null;
    }

    private void writePage(Page page, String pageFileName) throws Exception {
        String pageString = convertPageToString(page);
        FileProvider.writeData(FileProvider.PAGE_FOLDER, pageFileName, false, pageString);
    }

    private String convertPageToString(Page page) {
        return gson.toJson(page);
    }

    private String convertFeedListToString(Section section, List<MediaFeed> feedList) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(FileProvider.JSON_SECTION_ID_KEY, section.getId());
        jsonObject.add(FileProvider.JSON_FEED_LIST_KEY, gson.toJsonTree(feedList));
        return jsonObject.toString();
    }
}


