package com.example.thuyhien.simplelogin.ui.viewholder;

import android.view.View;

import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.ui.adapter.PosterAdapter;

import java.util.List;

/**
 * Created by thuyhien on 10/27/17.
 */

public class LandsSectionViewHolder extends SectionViewHolder {

    public LandsSectionViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void setAdapter(List<MediaFeed> mediaFeedList) {
        PosterAdapter landsPosterAdapter = new PosterAdapter(mediaFeedList, true);
        recyclerViewPoster.setAdapter(landsPosterAdapter);
    }
}
