package com.example.thuyhien.simplelogin.data.interactor.impl;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.data.interactor.DataCache;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.utils.FileProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/18/17.
 */

public class FileDataCache implements DataCache {

    private Gson gson;
    private File pageDir;
    private File feedDir;

    public FileDataCache(Gson gson, File pageDir, File feedDir) {
        this.gson = gson;
        this.pageDir = pageDir;
        this.feedDir = feedDir;
    }

    @Override
    public List<Page> getPageList() {
        File[] files = pageDir.listFiles();
        if (files != null && files.length > 0) {
            List<Page> pageList = new ArrayList<>();
            for (File file : files) {
                try {
                    Page page = convertStringToPage(FileProvider.readFile(file));
                    if (page != null) {
                        pageList.add(page);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return pageList;
        } else {
            return null;
        }
    }

    @Override
    public void savePageList(List<Page> pageList) {
        for (Page page : pageList) {
            savePage(page);
        }
    }

    @Override
    public void savePage(Page page) {
        if (page != null) {
            try {
                String pageFileName = FileProvider.BASE_PAGE_FILE_NAME + "_" + page.getId();
                String content = gson.toJson(page);
                File pageFile = new File(pageDir, pageFileName);
                FileProvider.writeFile(pageFile, false, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<MediaFeed> getFeedList(Section section) {
        String feedFileName = FileProvider.BASE_FEED_FILE_NAME + "_" + section.getId();
        try {
            File feedFile = new File(feedDir, feedFileName);
            String content = FileProvider.readFile(feedFile);
            if (content != null && !content.equals("")) {
                Type feedListOfEachSectionType = new TypeToken<List<MediaFeed>>() {
                }.getType();
                return gson.fromJson(content, feedListOfEachSectionType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveFeed(Section section, List<MediaFeed> mediaFeedList) {
        String feedFileName = FileProvider.BASE_FEED_FILE_NAME + "_" + section.getId();
        try {
            String feedListOfSection = convertFeedListToString(mediaFeedList);
            File feedFile = new File(feedDir, feedFileName);
            FileProvider.writeFile(feedFile, true, feedListOfSection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFeedListFileOfOnePage(Page page) {
        for (Section section : page.getSectionList()) {
            String feedFileName = FileProvider.BASE_FEED_FILE_NAME + "_" + section.getId();
            File feedFile = new File(feedDir, feedFileName);
            feedFile.delete();
        }
    }

    @Override
    public void deleteDataInFolder() {
        if (pageDir.isDirectory()) {
            for (File file : pageDir.listFiles()) {
                file.delete();
            }
        }
        if (feedDir.isDirectory()) {
            for (File file : feedDir.listFiles()) {
                file.delete();
            }
        }
    }

    private static String convertFeedListToString(List<MediaFeed> feedList) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(FileProvider.JSON_FEED_LIST_KEY,
                FoxApplication.getInstance().getDataGson().toJsonTree(feedList));
        return jsonObject.toString();
    }

    private Page convertStringToPage(String content) {
        if (content != null && !content.equals("")) {
            return gson.fromJson(content, Page.class);
        }
        return null;
    }
}


