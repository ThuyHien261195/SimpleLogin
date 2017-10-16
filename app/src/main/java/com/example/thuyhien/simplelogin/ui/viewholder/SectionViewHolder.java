package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.ui.adapter.PosterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class SectionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_title_section)
    TextView textViewTitleSection;

    @BindView(R.id.recycler_view_poster)
    RecyclerView recyclerViewPoster;

    private LinearLayoutManager linearLayoutManager;

    public SectionViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        linearLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
    }

    public void bindContentSection(Section section) {
        textViewTitleSection.setText(section.getMultiLangTitles().getTitle(FoxApplication.langCode));
        recyclerViewPoster.setLayoutManager(linearLayoutManager);
        PosterAdapter posterAdapter = new PosterAdapter(section.getFeedPostList());
        recyclerViewPoster.setAdapter(posterAdapter);
    }
}
