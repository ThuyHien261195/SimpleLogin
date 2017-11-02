package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.ui.viewholder.LandsPosterViewHolder;
import com.example.thuyhien.simplelogin.ui.viewholder.PosterViewHolder;

import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterViewHolder> {

    private List<MediaFeed> feedPostList;
    private boolean isTablet;

    public PosterAdapter(List<MediaFeed> feedPostList, boolean isTablet) {
        this.feedPostList = feedPostList;
        this.isTablet = isTablet;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poster, parent, false);
        if (!isTablet) {
            return new PosterViewHolder(rowView);
        } else {
            return new LandsPosterViewHolder(rowView);
        }
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        holder.bindImagePoster(feedPostList.get(position));

    }

    @Override
    public int getItemCount() {
        return feedPostList.size();
    }
}
