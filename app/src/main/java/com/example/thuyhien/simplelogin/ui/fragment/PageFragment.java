package com.example.thuyhien.simplelogin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitLoadDataInteractor;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.presenter.PagePresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PagePresenterImpl;
import com.example.thuyhien.simplelogin.ui.adapter.SectionAdapter;
import com.example.thuyhien.simplelogin.view.PageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PageFragment extends Fragment implements PageView {

    public static final String BUNDLE_SECTION_LIST = "SectionList";
    private List<Section> sectionList;
    private PagePresenter pagePresenter;

    @BindView(R.id.recycler_view_section)
    RecyclerView recyclerViewSection;

    public static PageFragment newInstance(ArrayList<Section> sectionList) {
        PageFragment postFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_SECTION_LIST, sectionList);
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
        pagePresenter.loadAllFeedList(sectionList);
    }

    @Override
    public void showAllFeedList(List<Section> sectionList) {
        initViews(sectionList);
    }

    @Override
    public void showMessageError(Exception ex) {
        // TODO
        Log.e("Page error", "page");
    }

    private void initViews(List<Section> sectionList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSection.setLayoutManager(linearLayoutManager);
        SectionAdapter sectionAdapter = new SectionAdapter(sectionList);
        recyclerViewSection.setAdapter(sectionAdapter);
    }

    private void getSectionBundle() {
        Serializable sectionSerializable = getArguments().getSerializable(BUNDLE_SECTION_LIST);
        sectionList = (ArrayList<Section>) sectionSerializable;
    }

    private void createPagePresenter() {
        LoadDataInteractor loadDataInteractor = new RetrofitLoadDataInteractor();
        pagePresenter = new PagePresenterImpl(this, loadDataInteractor);
    }
}