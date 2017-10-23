package com.example.thuyhien.simplelogin.data.interactor.impl;

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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyhien on 10/18/17.
 */

public class FileInteractorImpl implements FileInteractor {

    private Gson gson;
    private File pageDir;
    private File feedDir;

    public FileInteractorImpl(Gson gson, File pageDir, File feedDir) {
        this.gson = gson;
        this.pageDir = pageDir;
        this.feedDir = feedDir;
    }

    @Override
    public boolean getPageList(final LoadDataListener<List<Page>> listener) {
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
            listener.onLoadDataSuccess(pageList);
            return true;
        } else {
            listener.onLoadDataSuccess(null);
            return false;
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
    public boolean getFeedList(Page page, final LoadFeedListListener listener) {
        String feedFileName = FileProvider.BASE_FEED_FILE_NAME + "_" + page.getId();
        try {
            File feedFile = new File(feedDir, feedFileName);
            List<String> contentList = FileProvider.readFile(feedFile);
            return convertStringToFeedList(page, contentList, listener);
        } catch (IOException e) {
            e.printStackTrace();
            listener.onLoadDataFail(e);
        }
        return false;
    }

    @Override
    public void saveFeed(Page page, Section section,
                         List<MediaFeed> mediaFeedList) {
        String feedFileName = FileProvider.BASE_FEED_FILE_NAME + "_" + page.getId();
        try {
            String feedListOfSection = convertFeedListToString(section, mediaFeedList);
            File feedFile = new File(feedDir, feedFileName);
            FileProvider.writeFile(feedFile, true, feedListOfSection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearFeedFile(Page page) {
        String feedFileName = FileProvider.BASE_FEED_FILE_NAME + "_" + page.getId();
        try {
            File feedFile = new File(feedDir, feedFileName);
            FileProvider.clearFile(feedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkFeedFileListExits() {
        File[] files = feedDir.listFiles();
        return files != null && files.length > 0;
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

    private static String convertFeedListToString(Section section, List<MediaFeed> feedList) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(FileProvider.JSON_SECTION_ID_KEY, section.getId());
        jsonObject.add(FileProvider.JSON_FEED_LIST_KEY,
                FoxApplication.getInstance().getDataGson().toJsonTree(feedList));
        return jsonObject.toString();
    }

    private boolean convertStringToFeedList(Page page, List<String> contentList, LoadFeedListListener listener) throws IOException {
        List<Section> sectionList = page.getSectionList();
        if (contentList != null && contentList.size() > 0) {
            for (String item : contentList) {
                Type feedListOfEachSectionType = new TypeToken<Pair<String, List<MediaFeed>>>() {
                }.getType();
                Pair<String, List<MediaFeed>> feedListOfEachSection =
                        gson.fromJson(item, feedListOfEachSectionType);
                if (feedListOfEachSection != null) {
                    int pos = sectionList.indexOf(new Section(feedListOfEachSection.first));
                    if (pos != -1) {
                        listener.onLoadDataSuccess(sectionList.get(pos), feedListOfEachSection.second);
                    }
                }
            }
        } else {
            listener.onLoadDataFail(new FileException(FoxApplication.getInstance().getString(R.string.error_empty_file)));
            return false;
        }
        return true;
    }

    private Page convertStringToPage(List<String> contentList) {
        if (contentList != null && contentList.size() > 0) {
            return gson.fromJson(contentList.get(0), Page.class);
        }
        return null;
    }
}


