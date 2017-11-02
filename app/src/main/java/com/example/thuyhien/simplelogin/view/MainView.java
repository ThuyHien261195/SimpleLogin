package com.example.thuyhien.simplelogin.view;

import com.example.thuyhien.simplelogin.model.Page;

import java.util.List;

/**
 * Created by thuyhien on 10/9/17.
 */

public interface MainView extends BaseView {
    void showLoggedInView(String email);

    void showNotLogInView();

    void showPageList(List<Page> pageList);

    void getCurrentLangCode(String langCode);
}
