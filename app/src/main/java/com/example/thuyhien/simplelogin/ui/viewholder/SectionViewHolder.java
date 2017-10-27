package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.ui.adapter.PosterAdapter;

import java.util.List;

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

    public SectionViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPoster.setLayoutManager(linearLayoutManager);
    }

    public void bindContentSection(Section section, List<MediaFeed> mediaFeedList) {
        textViewTitleSection.setText(section.getMultiLangTitles().getTitle(FoxApplication.langCode));
        setAdapter(mediaFeedList);
    }

    protected void setAdapter(List<MediaFeed> mediaFeedList) {
        PosterAdapter posterAdapter = new PosterAdapter(mediaFeedList);
        recyclerViewPoster.setAdapter(posterAdapter);
    }
}
