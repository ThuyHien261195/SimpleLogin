package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.Section;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface PageView extends BaseView {
//    void getFeedList(List<FeedPost> feedPostList, int sectionIndex);

    void showAllFeedList(List<Section> sectionList);
}