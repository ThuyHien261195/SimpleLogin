package com.example.thuyhien.simplelogin.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.module.PageModule;
import com.example.thuyhien.simplelogin.data.network.exception.LoadDataException;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.ui.adapter.LandsSectionAdapter;
import com.example.thuyhien.simplelogin.ui.adapter.SectionAdapter;
import com.example.thuyhien.simplelogin.ui.listener.MainActivityListener;
import com.example.thuyhien.simplelogin.view.PageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PageFragment extends Fragment implements PageView {

    public static final String BUNDLE_PAGE = "Page";
    private Page page;
    private SectionAdapter sectionAdapter;
    private MainActivityListener mainActivityListener;
    private String langCode;

    @Inject
    PagePresenter pagePresenter;

    @Inject
    boolean isTablet;

    @BindView(R.id.swipe_media_feed_list)
    SwipeRefreshLayout swipeRefreshMediaFeedList;

    @BindView(R.id.recycler_view_section)
    RecyclerView recyclerViewSection;

    @BindView(R.id.progress_bar_loading)
    ProgressBar progressBarLoading;

    public static PageFragment newInstance(Page page) {
        PageFragment postFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_PAGE, page);
        postFragment.setArguments(bundle);
        return postFragment;
    }

    @Override
    public void onAttach(Context context) {
        try {
            mainActivityListener = (MainActivityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + getString(R.string.error_implement_main_activity_listener));
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((FoxApplication) getActivity().getApplication()).getAppComponent()
                .createPageComponent(new PageModule(this))
                .inject(this);

        getSectionBundle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pagePresenter.getCurrentLangCode();
        initViews();
        pagePresenter.loadAllFeedList(page, false);
    }

    @Override
    public void onDetach() {
        mainActivityListener = null;
        super.onDetach();
    }

    @Override
    public void showLoading() {
        recyclerViewSection.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBarLoading.setVisibility(View.GONE);
        recyclerViewSection.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayMediaFeedList(Section section, List<MediaFeed> mediaFeedList) {
        sectionAdapter.updateSection(section, mediaFeedList);
    }

    @Override
    public void displayRefreshPage(Page page) {
        mainActivityListener.onChangeTitlePage(page.getId(),
                page.getMultiLangTitles().getTitle(langCode));
        this.page = page;
        setAdapter(page.getSectionList());
        pagePresenter.loadAllFeedList(page, true);
        recyclerViewSection.scrollToPosition(0);
        swipeRefreshMediaFeedList.setRefreshing(false);
    }

    @Override
    public void showErrorMessage(Exception ex) {
        if (swipeRefreshMediaFeedList.isRefreshing()) {
            swipeRefreshMediaFeedList.setRefreshing(false);
            if (ex instanceof LoadDataException) {
                Toast.makeText(getContext(), R.string.error_refresh_data, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getCurrentLangCode(String langCode) {
        this.langCode = langCode;
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSection.setLayoutManager(linearLayoutManager);
        setAdapter(new ArrayList<Section>());

        swipeRefreshMediaFeedList.setColorSchemeColors(
                ContextCompat.getColor(getContext(), android.R.color.holo_blue_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_green_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_orange_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_red_light));

        swipeRefreshMediaFeedList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pagePresenter.loadPage(page);
            }
        });
    }

    private void setAdapter(List<Section> sectionList) {
        if (isTablet) {
            sectionAdapter = new LandsSectionAdapter(sectionList, langCode);
        } else {
            sectionAdapter = new SectionAdapter(sectionList, langCode);
        }
        recyclerViewSection.setAdapter(sectionAdapter);
    }

    private void getSectionBundle() {
        if (getArguments() != null) {
            page = (Page) getArguments().getSerializable(BUNDLE_PAGE);
        }
    }
}
