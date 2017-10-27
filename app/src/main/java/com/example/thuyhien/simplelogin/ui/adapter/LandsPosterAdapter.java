package com.example.thuyhien.simplelogin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.ui.viewholder.LandsPosterViewHolder;
import com.example.thuyhien.simplelogin.ui.viewholder.PosterViewHolder;

import java.util.List;

/**
 * Created by thuyhien on 10/27/17.
 */

public class LandsPosterAdapter extends PosterAdapter {

    public LandsPosterAdapter(List<MediaFeed> feedPostList) {
        super(feedPostList);
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poster, parent, false);
        return new LandsPosterViewHolder(view);
    }
}
