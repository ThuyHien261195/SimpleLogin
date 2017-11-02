package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.thuyhien.simplelogin.model.Page;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public class MediaFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Page> pageList;
    private String langCode;

    public MediaFragmentPagerAdapter(FragmentManager fm, List<Page> pageList, String langCode) {
        super(fm);
        this.pageList = pageList;
        this.langCode = langCode;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(pageList.get(position));
    }

    @Override
    public int getCount() {
        return pageList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageList.get(position).getMultiLangTitles().getTitle(langCode);
    }

    @Override
    public int getItemPosition(Object object) {
        return this.pageList.indexOf(object);
    }
}
