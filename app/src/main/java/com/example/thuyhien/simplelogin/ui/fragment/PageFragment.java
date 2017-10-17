package com.example.thuyhien.simplelogin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitLoadDataInteractor;
import com.example.thuyhien.simplelogin.data.network.retrofit.DataEndpointInterface;
import com.example.thuyhien.simplelogin.model.Page;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PagePresenterImpl;
import com.example.thuyhien.simplelogin.ui.adapter.SectionAdapter;
import com.example.thuyhien.simplelogin.view.PageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PageFragment extends Fragment implements PageView {

    public static final String BUNDLE_PAGE = "Page";
    private Page page;
    private PagePresenter pagePresenter;
    private SectionAdapter sectionAdapter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        createPagePresenter();
        initViews();
        pagePresenter.loadAllFeedList(page.getSectionList());
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

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSection.setLayoutManager(linearLayoutManager);
        sectionAdapter = new SectionAdapter(page.getSectionList());
        recyclerViewSection.setAdapter(sectionAdapter);
    }

    @Override
    public void displayMediaFeedList(Section section, int position) {
        sectionAdapter.udpateSection(section, position);
    }

    private void getSectionBundle() {
        if (getArguments() != null) {
            page = (Page) getArguments().getSerializable(BUNDLE_PAGE);
        }
    }

    private void createPagePresenter() {
        DataEndpointInterface dataApiService = FoxApplication.getInstance().getDataApiService();
        LoadDataInteractor loadDataInteractor = new RetrofitLoadDataInteractor(dataApiService);
        pagePresenter = new PagePresenterImpl(this, loadDataInteractor);
    }
}
