package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.ui.fragment.PageFragment;

import java.util.List;

/**
 * Created by thuyhien on 10/12/17.
 */

public class NewsPageFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Page> pageList;

    public NewsPageFragmentPagerAdapter(FragmentManager fm, List<Page> pageList) {
        super(fm);
        this.pageList = pageList;
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
        return pageList.get(position).getMultiLangTitles().getTitle(FoxApplication.langCode);
    }
}
